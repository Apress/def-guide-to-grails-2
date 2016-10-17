package com.gtunes

class Artist implements Serializable{
    static searchable = [only: ['name']]
    Date dateCreated
    Date lastUpdated

    String name
    static hasMany = [albums:Album]

    String toString() { name }
}
