package ro.tudorioan.attest.Utils;

import android.util.Log;

import java.util.List;
//
//import es.usc.citius.hipster.algorithm.Hipster;
//import es.usc.citius.hipster.graph.GraphBuilder;
//import es.usc.citius.hipster.graph.GraphSearchProblem;
//import es.usc.citius.hipster.graph.HipsterDirectedGraph;
//import es.usc.citius.hipster.model.problem.SearchProblem;
import ro.tudorioan.attest.Models.Rate;

public class Utils {


    public static Double getTraversedRate(List<Rate> rates, String fromCurrency, String toCurrency) {

        // TODO - implement a BFS traversal

        //
//        GraphBuilder<String, Double> graphBuilder = GraphBuilder.create();
//
//        for (Rate rate : rates) {
//            Double rateDbl = Double.parseDouble(rate.getRate());
//            graphBuilder.connect(rate.getFrom()).to(rate.getTo()).withEdge(rateDbl);
//        }
//
//        HipsterDirectedGraph<String,Double> graph = graphBuilder.createDirectedGraph();
//
//        SearchProblem p = GraphSearchProblem
//                .startingFrom(fromCurrency)
//                .in(graph)
//                .takeCostsFromEdges()
//                .build();

//        Log.e("SEARCH", Hipster.createDijkstra(p).search(toCurrency).toString());
        return 0d;
    }

}
