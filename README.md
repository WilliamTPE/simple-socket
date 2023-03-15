
# Simple Socket

A simple program implementation socket to transfer messages between two processes using Java.




## Description
- Processes
    - Process P1, running on Instance1, has access to a text file, F1, of size 300 bytes.
    - Process P2, running on Instance2, has access to a text file, F2, also of size 300 bytes.
    - The contents of these two text files are different.
- Either one of P1 or P2 initiates a socket connection with the other.
- Once the connection is established, the two processes do the following
    - P1 should send the original contents of F1 to P2 using four messages with the first message containing the first 75 bytes of F1, the next containing the next 75 bytes of F1 and so on.
    - Similarly, P2 should send the original contents of F2 to P1 using three messages with the first message containing the first 100 bytes of F1, the next containing the next 100 bytes of F1 and so on.
    - P1 should append the information received from P2 to the end of F1.
    - P2 should insert the information received from P1 in the beginning of F2.
- Two processes will communicate to each other that they have no more data to send. The socket connection should be terminated and the corresponding processes should exit.
- At the end of the steps described above, files F1 and F2 should be identical and of size 600 bytes.

```
+------+    Message 1 (first 75 bytes of F1)    +------+
|  P1  | ------------------------------------>  |  P2  |
+------+                                        +------+

+------+    Message 2 (next 75 bytes of F1)     +------+
|  P1  | ------------------------------------>  |  P2  |
+------+                                        +------+

+------+    Message 3 (next 75 bytes of F1)     +------+
|  P1  | ------------------------------------>  |  P2  |
+------+                                        +------+

+------+    Message 4 (remaining bytes of F1)  +------+
|  P1  | ------------------------------------> |  P2  |
+------+                                       +------+


+------+                                        +------+
|  P2  |    Message 1 (first 100 bytes of F2)   |  P1  |
+------+ ------------------------------------>  +------+

+------+     Message 2 (next 100 bytes of F2)   +------+
|  P2  | ------------------------------------>  |  P1  |
+------+                                        +------+

+------+     Message 3 (remaining bytes of F2)  +------+
|  P2  | ------------------------------------>  |  P1  |
+------+                                        +------+

```


## Run Locally

Start the program

```bash
  bash socket.sh
```


## License

[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)

