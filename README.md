# ChatUsingBroadcast
this a simple chat app using name broadcast. the chat launch follows this rules:
When initiating a messaging session, you will specify the name of the person you are trying to reach. This
should result in a message broadcast to everyone on the local network asking for the IP address
of the person. This message will be a String formatted as follows: 

          "????? name-of-other-person ##### your-name" 

Please note that the name of the person should be one word with no spaces.

Everyone will receive this message, but only the person wih that name will reply with a String formatted as follows: 

           "##### name-of-other-person ##### ww.xx.yy.zz" 




some pictures for the app:
launch the app using testSocket class
please note Iam using my name (aissa) to recieve broaddcast. you can change it in TestSocket class
