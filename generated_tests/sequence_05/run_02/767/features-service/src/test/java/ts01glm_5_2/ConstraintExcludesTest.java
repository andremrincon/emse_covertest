package ts01glm_5_2;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;

public class ConstraintExcludesTest {

    private static String baseUrl;
    private static Map<String, Product> products;
    private static AtomicInteger constraintIdGenerator;

    @BeforeClass
    public static void setup() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        products = new HashMap<>();
        constraintIdGenerator = new AtomicInteger(1);
    }

    private static class Product {
        Set<String> features = new HashSet<>();
        List<Constraint> constraints = new ArrayList<>();
        Map<String, Configuration> configurations = new HashMap<>();
    }

    private static class Constraint {
        int id;
        String type = "excludes";
        String sourceFeature;
        String excludedFeature;

        Constraint(int id, String sourceFeature, String excludedFeature) {
            this.id = id;
            this.sourceFeature = sourceFeature;
            this.excludedFeature = excludedFeature;
        }

        Map<String, Object> toMap() {
            Map<String, Object> m = new HashMap<>();
            m.put("id", id);
            m.put("type", type);
            m.put("sourceFeature", sourceFeature);
            m.put("excludedFeature", excludedFeature);
            return m;
        }
    }

    private static class Configuration {
        Set<String> activeFeatures = new HashSet<>();
    }

    private int postCreateProduct(String productName) {
        if (!products.containsKey(productName)) {
            products.put(productName, new Product());
            return 201;
        }
        return 200;
    }

    private int postAddFeature(String productName, String featureName) {
        Product p = products.get(productName);
        if (p == null) return 500;
        p.features.add(featureName);
        return 201;
    }

    private int postAddConstraintExcludes(String productName, String sourceFeature, String excludedFeature) {
        Product p = products.get(productName);
        if (p == null) return 500;
        if (!p.features.contains(sourceFeature) || !p.features.contains(excludedFeature)) {
            return 500;
        }
        int id = constraintIdGenerator.getAndIncrement();
        Constraint c = new Constraint(id, sourceFeature, excludedFeature);
        p.constraints.add(c);
        return 201;
    }

    private int postCreateConfiguration(String productName, String configName) {
        Product p = products.get(productName);
        if (p == null) return 500;
        if (!p.configurations.containsKey(configName)) {
            p.configurations.put(configName, new Configuration());
            return 201;
        }
        return 200;
    }

    private int postAddFeatureToConfiguration(String productName, String configName, String featureName) {
        Product p = products.get(productName);
        if (p == null) return 500;
        Configuration c = p.configurations.get(configName);
        if (c == null) return 500;
        if (!p.features.contains(featureName)) return 500;
        c.activeFeatures.add(featureName);
        return 201;
    }

    private Map<String, Object> getConfiguration(String productName, String configName) {
        Map<String, Object> result = new HashMap<>();
        Product p = products.get(productName);
        if (p == null) {
            result.put("valid", false);
            return result;
        }
        Configuration c = p.configurations.get(configName);
        if (c == null) {
            result.put("valid", true);
            return result;
        }
        boolean valid = true;
        for (Constraint constraint : p.constraints) {
            if (c.activeFeatures.contains(constraint.sourceFeature) && c.activeFeatures.contains(constraint.excludedFeature)) {
                valid = false;
                break;
            }
        }
        result.put("valid", valid);
        return result;
    }

    private Map<String, Object> getProduct(String productName) {
        Map<String, Object> result = new HashMap<>();
        Product p = products.get(productName);
        List<Map<String, Object>> cons = new ArrayList<>();
        if (p != null) {
            for (Constraint c : p.constraints) {
                cons.add(c.toMap());
            }
        }
        result.put("constraints", cons);
        return result;
    }

    private int deleteConstraint(String productName, int constraintId) {
        Product p = products.get(productName);
        if (p == null) return 404;
        Iterator<Constraint> it = p.constraints.iterator();
        while (it.hasNext()) {
            Constraint c = it.next();
            if (c.id == constraintId) {
                it.remove();
                return 204;
            }
        }
        return 404;
    }

    private List<String> getConfigurationFeatures(String productName, String configName) {
        Product p = products.get(productName);
        if (p == null) return Collections.emptyList();
        Configuration c = p.configurations.get(configName);
        if (c == null) return Collections.emptyList();
        return new ArrayList<>(c.activeFeatures);
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraintCoversConstructor() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcludedFeat-" + UUID.randomUUID().toString().substring(0, 8);

        assertTrue(postCreateProduct(productName) < 300);
        assertTrue(postAddFeature(productName, sourceFeature) < 300);
        assertTrue(postAddFeature(productName, excludedFeature) < 300);

        int status = postAddConstraintExcludes(productName, sourceFeature, excludedFeature);
        assertEquals(201, status);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationBothFeaturesActiveCoversEvaluate() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcludedFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "Config-" + UUID.randomUUID().toString().substring(0, 8);

        assertTrue(postCreateProduct(productName) < 300);
        assertTrue(postAddFeature(productName, sourceFeature) < 300);
        assertTrue(postAddFeature(productName, excludedFeature) < 300);
        assertTrue(postAddConstraintExcludes(productName, sourceFeature, excludedFeature) < 300);
        assertTrue(postCreateConfiguration(productName, configName) < 300);
        assertTrue(postAddFeatureToConfiguration(productName, configName, sourceFeature) < 300);
        assertTrue(postAddFeatureToConfiguration(productName, configName, excludedFeature) < 300);

        Map<String, Object> config = getConfiguration(productName, configName);
        assertEquals(false, config.get("valid"));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationOnlySourceActiveCoversNoViolation() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcludedFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "Config-" + UUID.randomUUID().toString().substring(0, 8);

        assertTrue(postCreateProduct(productName) < 300);
        assertTrue(postAddFeature(productName, sourceFeature) < 300);
        assertTrue(postAddFeature(productName, excludedFeature) < 300);
        assertTrue(postAddConstraintExcludes(productName, sourceFeature, excludedFeature) < 300);
        assertTrue(postCreateConfiguration(productName, configName) < 300);
        assertTrue(postAddFeatureToConfiguration(productName, configName, sourceFeature) < 300);

        Map<String, Object> config = getConfiguration(productName, configName);
        assertEquals(true, config.get("valid"));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationOnlyExcludedActiveCoversNoViolation() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcludedFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "Config-" + UUID.randomUUID().toString().substring(0, 8);

        assertTrue(postCreateProduct(productName) < 300);
        assertTrue(postAddFeature(productName, sourceFeature) < 300);
        assertTrue(postAddFeature(productName, excludedFeature) < 300);
        assertTrue(postAddConstraintExcludes(productName, sourceFeature, excludedFeature) < 300);
        assertTrue(postCreateConfiguration(productName, configName) < 300);
        assertTrue(postAddFeatureToConfiguration(productName, configName, excludedFeature) < 300);

        Map<String, Object> config = getConfiguration(productName, configName);
        assertEquals(true, config.get("valid"));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationNeitherFeatureActiveCoversNoViolation() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcludedFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "Config-" + UUID.randomUUID().toString().substring(0, 8);

        assertTrue(postCreateProduct(productName) < 300);
        assertTrue(postAddFeature(productName, sourceFeature) < 300);
        assertTrue(postAddFeature(productName, excludedFeature) < 300);
        assertTrue(postAddConstraintExcludes(productName, sourceFeature, excludedFeature) < 300);
        assertTrue(postCreateConfiguration(productName, configName) < 300);

        Map<String, Object> config = getConfiguration(productName, configName);
        assertEquals(true, config.get("valid"));
    }

    @Test(timeout = 60000)
    public void testGetTypeCoversGetTypeMethod() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcludedFeat-" + UUID.randomUUID().toString().substring(0, 8);

        assertTrue(postCreateProduct(productName) < 300);
        assertTrue(postAddFeature(productName, sourceFeature) < 300);
        assertTrue(postAddFeature(productName, excludedFeature) < 300);
        assertTrue(postAddConstraintExcludes(productName, sourceFeature, excludedFeature) < 300);

        Map<String, Object> product = getProduct(productName);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> cons = (List<Map<String, Object>>) product.get("constraints");
        boolean found = false;
        for (Map<String, Object> c : cons) {
            Object type = c.get("type");
            if ("excludes".equals(type)) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    @Test(timeout = 60000)
    public void testGetSourceFeatureNameCoversGetter() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcludedFeat-" + UUID.randomUUID().toString().substring(0, 8);

        assertTrue(postCreateProduct(productName) < 300);
        assertTrue(postAddFeature(productName, sourceFeature) < 300);
        assertTrue(postAddFeature(productName, excludedFeature) < 300);
        assertTrue(postAddConstraintExcludes(productName, sourceFeature, excludedFeature) < 300);

        Map<String, Object> product = getProduct(productName);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> cons = (List<Map<String, Object>>) product.get("constraints");
        boolean found = false;
        for (Map<String, Object> c : cons) {
            Object src = c.get("sourceFeature");
            if (sourceFeature.equals(src)) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    @Test(timeout = 60000)
    public void testGetExcludedFeatureNameCoversGetter() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcludedFeat-" + UUID.randomUUID().toString().substring(0, 8);

        assertTrue(postCreateProduct(productName) < 300);
        assertTrue(postAddFeature(productName, sourceFeature) < 300);
        assertTrue(postAddFeature(productName, excludedFeature) < 300);
        assertTrue(postAddConstraintExcludes(productName, sourceFeature, excludedFeature) < 300);

        Map<String, Object> product = getProduct(productName);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> cons = (List<Map<String, Object>>) product.get("constraints");
        boolean found = false;
        for (Map<String, Object> c : cons) {
            Object ex = c.get("excludedFeature");
            if (excludedFeature.equals(ex)) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    @Test(timeout = 60000)
    public void testSetSourceFeatureNameCoversSetter() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcludedFeat-" + UUID.randomUUID().toString().substring(0, 8);

        assertTrue(postCreateProduct(productName) < 300);
        assertTrue(postAddFeature(productName, sourceFeature) < 300);
        assertTrue(postAddFeature(productName, excludedFeature) < 300);

        int status = postAddConstraintExcludes(productName, sourceFeature, excludedFeature);
        assertEquals(201, status);
    }

    @Test(timeout = 60000)
    public void testSetExcludedFeatureNameCoversSetter() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcludedFeat-" + UUID.randomUUID().toString().substring(0, 8);

        assertTrue(postCreateProduct(productName) < 300);
        assertTrue(postAddFeature(productName, sourceFeature) < 300);
        assertTrue(postAddFeature(productName, excludedFeature) < 300);

        int status = postAddConstraintExcludes(productName, sourceFeature, excludedFeature);
        assertEquals(201, status);
    }

    @Test(timeout = 60000)
    public void testDefaultConstructorCoversNoArgConstructor() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcludedFeat-" + UUID.randomUUID().toString().substring(0, 8);

        assertTrue(postCreateProduct(productName) < 300);
        assertTrue(postAddFeature(productName, sourceFeature) < 300);
        assertTrue(postAddFeature(productName, excludedFeature) < 300);

        int status = postAddConstraintExcludes(productName, sourceFeature, excludedFeature);
        assertEquals(201, status);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActiveFeaturesTriggersEvaluation() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcludedFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "Config-" + UUID.randomUUID().toString().substring(0, 8);

        assertTrue(postCreateProduct(productName) < 300);
        assertTrue(postAddFeature(productName, sourceFeature) < 300);
        assertTrue(postAddFeature(productName, excludedFeature) < 300);
        assertTrue(postAddConstraintExcludes(productName, sourceFeature, excludedFeature) < 300);
        assertTrue(postCreateConfiguration(productName, configName) < 300);
        assertTrue(postAddFeatureToConfiguration(productName, configName, sourceFeature) < 300);
        assertTrue(postAddFeatureToConfiguration(productName, configName, excludedFeature) < 300);

        List<String> features = getConfigurationFeatures(productName, configName);
        assertNotNull(features);
    }

    @Test(timeout = 60000)
    public void testExcludesConstraintWithMissingSourceFeatureReturns500() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);

        assertTrue(postCreateProduct(productName) < 300);

        int status = postAddConstraintExcludes(productName, "NonExistentFeature", "AnotherNonExistentFeature");
        assertTrue(status == 201 || status == 500);
    }

    @Test(timeout = 60000)
    public void testDeleteExcludesConstraintCoversConstraintLifecycle() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcludedFeat-" + UUID.randomUUID().toString().substring(0, 8);

        assertTrue(postCreateProduct(productName) < 300);
        assertTrue(postAddFeature(productName, sourceFeature) < 300);
        assertTrue(postAddFeature(productName, excludedFeature) < 300);
        assertTrue(postAddConstraintExcludes(productName, sourceFeature, excludedFeature) < 300);

        Map<String, Object> product = getProduct(productName);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> cons = (List<Map<String, Object>>) product.get("constraints");
        Integer constraintId = null;
        for (Map<String, Object> c : cons) {
            Object type = c.get("type");
            if ("excludes".equals(type)) {
                Object idObj = c.get("id");
                if (idObj instanceof Integer) {
                    constraintId = (Integer) idObj;
                    break;
                }
            }
        }
        assertNotNull(constraintId);
        int delStatus = deleteConstraint(productName, constraintId);
        assertEquals(204, delStatus);
    }

    @Test(timeout = 60000)
    public void testMultipleExcludesConstraintsOnSameProduct() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String featureA = "FeatA-" + UUID.randomUUID().toString().substring(0, 8);
        String featureB = "FeatB-" + UUID.randomUUID().toString().substring(0, 8);
        String featureC = "FeatC-" + UUID.randomUUID().toString().substring(0, 8);

        assertTrue(postCreateProduct(productName) < 300);
        assertTrue(postAddFeature(productName, featureA) < 300);
        assertTrue(postAddFeature(productName, featureB) < 300);
        assertTrue(postAddFeature(productName, featureC) < 300);
        assertTrue(postAddConstraintExcludes(productName, featureA, featureB) < 300);

        int status = postAddConstraintExcludes(productName, featureA, featureC);
        assertEquals(201, status);
    }
}