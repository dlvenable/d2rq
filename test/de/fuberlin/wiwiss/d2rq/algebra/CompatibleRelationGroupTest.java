package de.fuberlin.wiwiss.d2rq.algebra;

import java.util.Collections;
import java.util.Set;

import junit.framework.TestCase;
import de.fuberlin.wiwiss.d2rq.algebra.AliasMap;
import de.fuberlin.wiwiss.d2rq.algebra.Attribute;
import de.fuberlin.wiwiss.d2rq.algebra.RelationImpl;
import de.fuberlin.wiwiss.d2rq.expr.Expression;
import de.fuberlin.wiwiss.d2rq.sql.DummyDB;

public class CompatibleRelationGroupTest extends TestCase {

	public void testNotUniqueIsNotCompatible() {
		Set projections1 = Collections.singleton(new Attribute(null, "table", "unique"));
		Set projections2 = Collections.singleton(new Attribute(null, "table", "not_unique"));
		RelationImpl unique = new RelationImpl(
				new DummyDB(), AliasMap.NO_ALIASES, Expression.TRUE, Collections.EMPTY_SET, 
				projections1, true, null, false, Relation.NO_LIMIT, Relation.NO_LIMIT);
		RelationImpl notUnique = new RelationImpl(
				new DummyDB(), AliasMap.NO_ALIASES, Expression.TRUE, Collections.EMPTY_SET, 
				projections2, false, null, false, Relation.NO_LIMIT, Relation.NO_LIMIT);
		assertTrue(new CompatibleRelationGroup(unique).isCompatible(unique));
		assertFalse(new CompatibleRelationGroup(unique).isCompatible(notUnique));
		assertFalse(new CompatibleRelationGroup(notUnique).isCompatible(unique));
	}
}
