package pdk.chart.swing;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * A base class for creating the main frame for simple applications.  The frame
 * listens for window closing events, and responds by shutting down the JVM.
 * This is OK for small demo applications...for more serious applications,
 * you'll want to use something more robust.
 */
public class ApplicationFrame extends JFrame implements WindowListener {

    /**
     * Constructs a new application frame.
     *
     * @param title the frame title.
     */
    public ApplicationFrame(String title) {
        super(title);
        addWindowListener(this);
    }

    /**
     * Listens for the main window closing, and shuts down the application.
     *
     * @param event information about the window event.
     */
    @Override
    public void windowClosing(WindowEvent event) {
        if (event.getWindow() == this) {
            dispose();
            System.exit(0);
        }
    }

    /**
     * Required for WindowListener interface, but not used by this class.
     *
     * @param event information about the window event.
     */
    @Override
    public void windowClosed(WindowEvent event) {
        // ignore
    }

    /**
     * Required for WindowListener interface, but not used by this class.
     *
     * @param event information about the window event.
     */
    @Override
    public void windowActivated(WindowEvent event) {
        // ignore
    }

    /**
     * Required for WindowListener interface, but not used by this class.
     *
     * @param event information about the window event.
     */
    @Override
    public void windowDeactivated(WindowEvent event) {
        // ignore
    }

    /**
     * Required for WindowListener interface, but not used by this class.
     *
     * @param event information about the window event.
     */
    @Override
    public void windowDeiconified(WindowEvent event) {
        // ignore
    }

    /**
     * Required for WindowListener interface, but not used by this class.
     *
     * @param event information about the window event.
     */
    @Override
    public void windowIconified(WindowEvent event) {
        // ignore
    }

    /**
     * Required for WindowListener interface, but not used by this class.
     *
     * @param event information about the window event.
     */
    @Override
    public void windowOpened(WindowEvent event) {
        // ignore
    }

}
