package com.gtunes

class Artist implements Serializable{
    Date dateCreated
    Date lastUpdated

    String name
    static hasMany = [albums:Album]

    String toString() { name }
}
