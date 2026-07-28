package org.javiermf.features.models.constraints;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QConstraintExcludes is a Querydsl query type for ConstraintExcludes
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QConstraintExcludes extends EntityPathBase<ConstraintExcludes> {

    private static final long serialVersionUID = -1054103243L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QConstraintExcludes constraintExcludes = new QConstraintExcludes("constraintExcludes");

    public final QFeatureConstraint _super;

    public final StringPath excludedFeatureName = createString("excludedFeatureName");

    // inherited
    public final org.javiermf.features.models.QProduct forProduct;

    //inherited
    public final NumberPath<Long> id;

    public final StringPath sourceFeatureName = createString("sourceFeatureName");

    public QConstraintExcludes(String variable) {
        this(ConstraintExcludes.class, forVariable(variable), INITS);
    }

    public QConstraintExcludes(Path<? extends ConstraintExcludes> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QConstraintExcludes(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QConstraintExcludes(PathMetadata<?> metadata, PathInits inits) {
        this(ConstraintExcludes.class, metadata, inits);
    }

    public QConstraintExcludes(Class<? extends ConstraintExcludes> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QFeatureConstraint(type, metadata, inits);
        this.forProduct = _super.forProduct;
        this.id = _super.id;
    }

}

