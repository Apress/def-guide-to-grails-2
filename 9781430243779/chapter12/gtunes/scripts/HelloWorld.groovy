includeTargets << grailsScript("_GrailsInit")

target(main: "Prints a Hello message") {
    def message = "Hello ${argsMap.params ? argsMap.params[0] : 'World'}"
    if(argsMap.uppercase) {
        println message.toUpperCase()
    } else {
        println message
    }
}

setDefaultTarget(main)

USAGE = '''
    hello-world [-uppercase] [NAME]

where
    -uppercase = Convert the message to all uppercase
    NAME       = The name of the person to say hello to
'''
