package org.javiermf.features.models;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QProductConfiguration is a Querydsl query type for ProductConfiguration
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QProductConfiguration extends EntityPathBase<ProductConfiguration> {

    private static final long serialVersionUID = 1740346542L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductConfiguration productConfiguration = new QProductConfiguration("productConfiguration");

    public final SetPath<Feature, QFeature> activedFeatures = this.<Feature, QFeature>createSet("activedFeatures", Feature.class, QFeature.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final QProduct product;

    public final BooleanPath valid = createBoolean("valid");

    public QProductConfiguration(String variable) {
        this(ProductConfiguration.class, forVariable(variable), INITS);
    }

    public QProductConfiguration(Path<? extends ProductConfiguration> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProductConfiguration(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProductConfiguration(PathMetadata<?> metadata, PathInits inits) {
        this(ProductConfiguration.class, metadata, inits);
    }

    public QProductConfiguration(Class<? extends ProductConfiguration> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product")) : null;
    }

}

