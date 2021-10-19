package com.anish.monsters;

public class SelectSorter<T extends Comparable<T>> implements Sorter<T> {
    private T[] a;

    @Override
    public void load(T[] a) {
        this.a = a;
    }

    private void swap(int i, int j) {
        T temp;
        temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        plan += "" + a[i] + "<->" + a[j] + "\n";
    }

    private String plan = "";

    @Override
    public void sort() {
        for(int i = 0; i < a.length-1; i++)
        {
            int min = i;
            for(int j = i+1; j < a.length; j++)
            {
                if(a[j].compareTo(a[min]) < 0)
                {
                    min = j;
                }
            }
            if(min!=i)
            {
                swap(i, min);
            }
        }
    }

    @Override
    public String getPlan() {
        return this.plan;
    }

}