//
// /  D2RQPatternStage.java
//  d2rq-map
//
//  Created by Joerg Garbers on 25.02.05.
//  Copyright 2005 Joerg Garbers. All rights reserved.
//
package de.fuberlin.wiwiss.d2rq;

import com.hp.hpl.jena.graph.Graph;
import com.hp.hpl.jena.graph.Triple;
import com.hp.hpl.jena.graph.query.Domain;
import com.hp.hpl.jena.graph.query.ExpressionSet;
import com.hp.hpl.jena.graph.query.Mapping;
import com.hp.hpl.jena.graph.query.PatternStage;
import com.hp.hpl.jena.graph.query.Pattern;
import com.hp.hpl.jena.graph.query.Pipe;
import com.hp.hpl.jena.graph.query.ValuatorSet;
import com.hp.hpl.jena.util.iterator.ClosableIterator;

public class D2RQPatternStage2 extends CombinedPatternStage {

	private GraphD2RQ graph;

	private ExpressionSet constraints;

	// instanciate just one PatternQueryCombiner? it could do some caching
	// or leave the caching for graph? e.g. triple -> list of bridges

	public D2RQPatternStage2(GraphD2RQ graph, Mapping map,
			ExpressionSet constraints, Triple[] triples) {
		super((Graph) graph, map, constraints, triples);
		// some contraints are eaten up at this point!
		// so use s.th. like a clone() and setter method at invocation time
		D2RQPatternStage2.this.graph = graph;
		D2RQPatternStage2.this.constraints = constraints;
	}

	protected ClosableIterator resultIteratorForTriplePattern(Triple[] triples) {
		PatternQueryCombiner combiner = new PatternQueryCombiner(graph,
				varInfo, constraints, triples); // pass map?
		combiner.setup();
		// maybe it would be more efficient to reduce matching by checking
		// just the difference set
		// between the resultTriples and the previously successfully matched
		// resultTriples
		ClosableIterator it = combiner.resultTriplesIterator();
		return it;
	}

} // class