package net.netty.server.startup;

import java.io.IOException;

import net.netty.server.thread.AIOAsyncServer;

public class AIOServer 
{
    public static void main( String[] args ) throws IOException
    {
        AIOAsyncServer aioAsyncServer = new AIOAsyncServer();
        
        new Thread(aioAsyncServer, "AIO-Server").start();
    }
}
