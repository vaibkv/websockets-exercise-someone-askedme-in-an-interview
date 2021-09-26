## Requirements

### Building a chat server

#### Tech - plan sockets library from Java, akka-http(in prod, but learning curve!), Scala

**Functional Requirements**
1. Chat server - accept client connections, able to have bi-directional chat(client to server, server to client, client to client), 
   ability to log in, log off 
   Main Requirement(Direct Messaging) - Two clients being able to send msgs to each other
2. Secondary requirements - group chat

**Design**
1. Main component - server side component (socket listening)
2. For dev testing and development - telnet as client
3. Collection (user details and sockets): User1 to user2 --> server (fetches details for user2) and sends message to user2 and vice-versa

**Commands** - 
- :login <username> <password> (<password> is secondary)
- :msg <user> <msg body>
- :quit
- :broadcast <msg body>
  
Groups - 
- :join <groupName> -- step1
- :group <groupName> <msg body> -- step2
- :leave group <group name> -- step2
   

Assumptions for MVP
1. Line by line chat, not multiline

More Links -

http://pirate.shu.edu/~wachsmut/Teaching/CSAS2214/Virtual/Lectures/chat-client-server.html
https://www.eecs.yorku.ca/course_archive/2011-12/W/3214/j-chat-ltr.pdf
https://gyawaliamit.medium.com/multi-client-chat-server-using-sockets-and-threads-in-java-2d0b64cad4a7
https://www.codejava.net/java-se/networking/how-to-create-a-chat-console-application-in-java-using-socket
https://github.com/johanandren/chat-with-akka-http-websockets/blob/master/src/main/scala/chat/Server.scala
https://doc.akka.io/docs/akka/1.3.1/scala/tutorial-chat-server.html
https://www.baeldung.com/java-websockets 

See youtube videos in playlist as well