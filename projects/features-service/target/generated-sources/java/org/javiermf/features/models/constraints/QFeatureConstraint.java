package org.javiermf.features.models.constraints;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QFeatureConstraint is a Querydsl query type for FeatureConstraint
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QFeatureConstraint extends EntityPathBase<FeatureConstraint> {

    private static final long serialVersionUID = 432368436L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFeatureConstraint featureConstraint = new QFeatureConstraint("featureConstraint");

    public final org.javiermf.features.models.QProduct forProduct;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QFeatureConstraint(String variable) {
        this(FeatureConstraint.class, forVariable(variable), INITS);
    }

    public QFeatureConstraint(Path<? extends FeatureConstraint> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QFeatureConstraint(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QFeatureConstraint(PathMetadata<?> metadata, PathInits inits) {
        this(FeatureConstraint.class, metadata, inits);
    }

    public QFeatureConstraint(Class<? extends FeatureConstraint> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.forProduct = inits.isInitialized("forProduct") ? new org.javiermf.features.models.QProduct(forProperty("forProduct")) : null;
    }

}

