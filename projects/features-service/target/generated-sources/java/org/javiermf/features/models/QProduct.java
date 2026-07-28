package org.javiermf.features.models;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QProduct is a Querydsl query type for Product
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QProduct extends EntityPathBase<Product> {

    private static final long serialVersionUID = 1830170920L;

    public static final QProduct product = new QProduct("product");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final SetPath<org.javiermf.features.models.constraints.FeatureConstraint, org.javiermf.features.models.constraints.QFeatureConstraint> productFeatureConstraints = this.<org.javiermf.features.models.constraints.FeatureConstraint, org.javiermf.features.models.constraints.QFeatureConstraint>createSet("productFeatureConstraints", org.javiermf.features.models.constraints.FeatureConstraint.class, org.javiermf.features.models.constraints.QFeatureConstraint.class, PathInits.DIRECT2);

    public final SetPath<Feature, QFeature> productFeatures = this.<Feature, QFeature>createSet("productFeatures", Feature.class, QFeature.class, PathInits.DIRECT2);

    public QProduct(String variable) {
        super(Product.class, forVariable(variable));
    }

    public QProduct(Path<? extends Product> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProduct(PathMetadata<?> metadata) {
        super(Product.class, metadata);
    }

}

