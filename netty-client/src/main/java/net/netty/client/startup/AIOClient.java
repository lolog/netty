package net.netty.client.startup;

import java.io.IOException;

import net.netty.client.handler.AIOAsyncClient;

public class AIOClient 
{
    public static void main( String[] args ) throws IOException
    {
        AIOAsyncClient aioAsyncClient = new AIOAsyncClient();
        
        new Thread(aioAsyncClient, "AIO-Client").start();
    }
}
