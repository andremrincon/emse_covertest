package org.javiermf.features.models.constraints;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QConstraintRequires is a Querydsl query type for ConstraintRequires
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QConstraintRequires extends EntityPathBase<ConstraintRequires> {

    private static final long serialVersionUID = 853669642L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QConstraintRequires constraintRequires = new QConstraintRequires("constraintRequires");

    public final QFeatureConstraint _super;

    // inherited
    public final org.javiermf.features.models.QProduct forProduct;

    //inherited
    public final NumberPath<Long> id;

    public final StringPath requiredFeatureName = createString("requiredFeatureName");

    public final StringPath sourceFeatureName = createString("sourceFeatureName");

    public QConstraintRequires(String variable) {
        this(ConstraintRequires.class, forVariable(variable), INITS);
    }

    public QConstraintRequires(Path<? extends ConstraintRequires> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QConstraintRequires(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QConstraintRequires(PathMetadata<?> metadata, PathInits inits) {
        this(ConstraintRequires.class, metadata, inits);
    }

    public QConstraintRequires(Class<? extends ConstraintRequires> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QFeatureConstraint(type, metadata, inits);
        this.forProduct = _super.forProduct;
        this.id = _super.id;
    }

}

