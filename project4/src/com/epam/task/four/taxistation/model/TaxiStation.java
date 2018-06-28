package com.epam.task.four.taxistation.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity class for a list of given Cabs
 * @author Alexey Yermachyonok
 * @version 1.0
 */

public class TaxiStation {

    private List<Cab> cabList = new ArrayList<Cab>();
    
    public List<Cab> getCabList() {
        return cabList;
    }

    public void setCabList(List<Cab> cabList) {
        this.cabList = cabList;
    }
    
    /**
     * Adds a new Cab into the list of cabs.
     * @see Cab
     */
    public void addCab(Cab cab) {
        cabList.add(cab);
    }
    
    /**
     * Use this to get the total amount of Cabs
     * @return int number of cabs
     * @see Cab
     */
    public int getSize() {
        return cabList.size();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(this.getClass().getSimpleName());
        s.append("TaxiStation [cabList=");
        s.append(cabList);
        s.append("]");
        
        return s.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cabList == null) ? 0 : cabList.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TaxiStation other = (TaxiStation) obj;
        if (cabList == null) {
            if (other.cabList != null)
                return false;
        } else if (!cabList.equals(other.cabList))
            return false;
        return true;
    }
    
}
