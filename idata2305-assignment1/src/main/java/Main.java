import servers.SingleThreadedServer;

import servers.MultiThreadedServer;

    class Main {

        public static void main(String[] args) throws Exception {
    //    System.out.println("Starting SingleThreaded server");
    //    SingleThreadedServer server = new SingleThreadedServer(8080);
    //    Thread serverThread = new Thread(server);
    //    serverThread.start();

          System.out.println("Starting MultiThreaded server");
          MultiThreadedServer server = new MultiThreadedServer(8080);
          Thread serverThread = new Thread(server);
          serverThread.start();
        }
    }