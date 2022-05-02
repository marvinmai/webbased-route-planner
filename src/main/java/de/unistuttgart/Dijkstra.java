package de.unistuttgart;

import java.util.*;

public class Dijkstra {

    DataSet data = DataSetReader.dataSet;
    public Optional<Integer> findNextNodeID(int nodeID){

        List<Integer> src = new LinkedList<>();
        List<Integer> trg = new LinkedList<>();
        List<Integer> cst = new LinkedList<>();
        Optional<Integer> nextNodeID;
        /**
         * Adding the src = trg to the "matrix" with the cost of 0
         */
        src.add(nodeID);
        trg.add(nodeID);
        cst.add(0);

        /**
         * Adding the other entries to the matrix
         */
        for(int i = 0; i < data.getSourceNodeIds().length; i++){
            if(data.getSourceNodeIds()[i] == nodeID){
                src.add(nodeID);
                trg.add(data.getTargetNodeIds()[i]);
                cst.add(data.getSourceToTargetCosts()[i]);
            }
        }

        if(src.size() > 1){

            int shortestPathToNextNodeIdx = Integer.MAX_VALUE;
            /**
             * Check what node is most cost efficent and get its index
             */
            for(int i = 1; i < cst.size(); i++){
                int tmpValue = cst.get(i);
                if(tmpValue < shortestPathToNextNodeIdx){
                    shortestPathToNextNodeIdx = tmpValue;
                }
            }
            /**
             * Find the closest node using the previously found nodeID index
             */
            nextNodeID = Optional.ofNullable(trg.get(cst.indexOf(shortestPathToNextNodeIdx)));
            return nextNodeID;
        }
        else {
            return Optional.empty();
        }

    }
}
