/*
  (c) Copyright 2005 by Joerg Garbers (jgarbers@zedat.fu-berlin.de)
*/

package de.fuberlin.wiwiss.d2rq.rdql;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.fuberlin.wiwiss.d2rq.GraphD2RQ;


import com.hp.hpl.jena.graph.Graph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.graph.Triple;
import com.hp.hpl.jena.graph.query.BindingQueryPlan;
import com.hp.hpl.jena.graph.query.ExpressionSet;
import com.hp.hpl.jena.graph.query.Mapping;
import com.hp.hpl.jena.graph.query.PatternStage;
import com.hp.hpl.jena.graph.query.Pipe;
import com.hp.hpl.jena.graph.query.Query;
import com.hp.hpl.jena.graph.query.QueryHandler;
import com.hp.hpl.jena.graph.query.Stage;
import com.hp.hpl.jena.graph.query.SimpleQueryHandler;

/**
a D2RQQueryHandler handles queries on behalf of a GraphD2RQ graph. 
subclassing from SimpleQueryHandler for convenience, makes differences clear.
**/

public class D2RQQueryHandler extends SimpleQueryHandler implements QueryHandler {
	private GraphD2RQ graph; // see also SimpleQueryHandler.graph
	private boolean doFastpath;  // if true, enable fastpath optimization

    public D2RQQueryHandler( Graph graph ) {
        super(graph);
		this.graph = (GraphD2RQ)graph;
		doFastpath = true;
    }     
   
    public static final boolean runVersion2=true;
    
    public Stage patternStage( Mapping map, ExpressionSet constraints, Triple [] t )
    { 
    		if (runVersion2) {
    		    D2RQPatternStage2 inst= new D2RQPatternStage2( graph, map, constraints, t );
    			inst.setup();
    			return inst;
    		} else {
    		    // D2RQPatternStage does not compile any more under Jena 2.3
    		    // The new Jena implementation of PatternStage should be examined.
    		    // Maybe it resolves the errors found in the last version.
    		    // See comments in CombinedPatternStage. 
    		    // But anyhow, we still have the better D2RQPatternStage2.
    		    // return new D2RQPatternStage( graph, map, constraints, t );  // jg see PatternStage for overriding
    			return null; 
    		}
    }
}