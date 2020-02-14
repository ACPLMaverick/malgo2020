package com.acplmaverick;

public class Main
{
                  //typ zwracany      //nazwa     //lista argumentow
    public static void                funkcja     (int argument1, String argument2)
    {   // blok kodu w klamerkach
        System.out.println("Dupa.");
        System.out.println(argument1);
        System.out.println(argument2);
    }

    public static void funkcja(int argument1)
    {
        System.out.println("Lol.");
        System.out.println(argument1);
    }

    public static int bar(int argument)
    {
        if(argument < 0)
        {
            return argument;
        }

        argument = argument * 2;
        return argument;
    }

    public static void main(String[] args)
    {
        /*
        int a = 5;

        boolean val = false;

        switch (a)
        {
            case 1:
                System.out.println("Jeden");
                break;
            case 2:
                System.out.println("Dwa");
                break;
            default:
                System.out.println("Nie wiem");
                break;
        }

        System.out.println(bar(5));

        float v = 5 / 2;
        System.out.println(v);

        //funkcja(a);
        */

        byte vByte = (byte)-1;
        char vChar = (char)vByte;

        System.out.println("vByte:");
        System.out.println(vByte);
        System.out.println("vChar:");
        System.out.println((int)vChar);
    }
}
