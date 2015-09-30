package dk.au.cs.pp15g14project2.reporters;

import android.location.*;
import android.os.Bundle;
import dk.au.cs.pp15g14project2.loggers.Logger;
import dk.au.cs.pp15g14project2.utilities.LocationPrinter;

public class TimeReporter implements Reporter
{
    private static final String TAG = "TimeReporter";
    private static final String GPS = LocationManager.GPS_PROVIDER;
    
    private final int timeInterval;
    private final LocationManager locationManager;
    private final LocationListener listener;
    
    public TimeReporter(final LocationManager locationManager,
                        final Logger logger,
                        final int timeInterval /* seconds */)
    {
        this.timeInterval = 1000 * timeInterval;
        this.locationManager = locationManager;
        this.listener = new LocationListener()
        {
            public void onLocationChanged(final Location location)
            {
                logger.log(TAG, LocationPrinter.convertToString(location));
            }
            
            public void onStatusChanged(final String provider, final int status, final Bundle extras)
            {
            }
            
            public void onProviderEnabled(final String provider)
            {
            }
            
            public void onProviderDisabled(final String provider)
            {
            }
        };
    }
    
    public void listenForUpdates()
    {
        locationManager.requestLocationUpdates(GPS, timeInterval, 0, listener);
    }
    
    public void stopListeningForUpdates()
    {
        locationManager.removeUpdates(listener);
    }
}
