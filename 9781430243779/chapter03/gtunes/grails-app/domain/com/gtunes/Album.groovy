package com.gtunes

class Album implements Serializable{
    
    String title
    Integer year
    String genre
    
    static constraints = {
        title blank:false
        year range:1900..2100
    }

    String toString() { title }
}
