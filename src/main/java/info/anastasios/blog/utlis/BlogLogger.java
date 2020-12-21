package info.anastasios.blog.utlis;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class BlogLogger
{
    public static FileHandler fh = null;
    public static ConsoleHandler ch = null;


    public static Logger getLogger(String className)
    {
        Logger myLogger = Logger.getLogger(className);
        myLogger.setLevel(Level.FINEST);
        myLogger.setUseParentHandlers(false);

        if(ch == null)
        {
            ch = new ConsoleHandler();
            ch.setLevel(Level.FINEST);
        }

        if(fh == null)
        {
            try
            {
                fh = new FileHandler("logs.log", true);
            }
            catch (SecurityException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            fh.setLevel(Level.ALL);
            fh.setFormatter(new SimpleFormatter());
        }

        myLogger.addHandler(ch);
        myLogger.addHandler(fh);

        return myLogger;
    }
}
