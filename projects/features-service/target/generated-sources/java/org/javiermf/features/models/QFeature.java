package org.javiermf.features.models;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QFeature is a Querydsl query type for Feature
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QFeature extends EntityPathBase<Feature> {

    private static final long serialVersionUID = 1160437551L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFeature feature = new QFeature("feature");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final SetPath<ProductConfiguration, QProductConfiguration> inConfigurations = this.<ProductConfiguration, QProductConfiguration>createSet("inConfigurations", ProductConfiguration.class, QProductConfiguration.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final QProduct product;

    public QFeature(String variable) {
        this(Feature.class, forVariable(variable), INITS);
    }

    public QFeature(Path<? extends Feature> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QFeature(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QFeature(PathMetadata<?> metadata, PathInits inits) {
        this(Feature.class, metadata, inits);
    }

    public QFeature(Class<? extends Feature> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product")) : null;
    }

}

