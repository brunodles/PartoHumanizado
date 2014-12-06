/*
 * Copyright 2014 de [PARTO HUMANIZADO/SERGIO HOLANDA,MARCELA OLIVEIRA E BRUNO LIMA] Este arquivo é parte do programa [PARTO HUMANIZADO]. O [PARTO
 * HUMANIZADO]é um software livre; você pode redistribuí-lo e/ou modificá-lo dentro dos termos da [GNU General Public License OU GNU Affero General Public
 * License] como publicada pela Fundação do Software Livre (FSF); na versão 3 da Licença. Este programa é distribuído na esperança que possa ser útil, mas
 * SEM NENHUMA GARANTIA;
 * sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a licença para maiores detalhes. Você deve ter recebido uma
 * cópia da [GNU General Public License OU GNU Affero General Public License], sob o título "LICENCA.txt", junto com este programa, ,
 * se não, acesse http://www.gnu.org/licenses/
 */

package br.com.PartoHumanizado.view;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by sergio holanda on 22-Nov-14.
 */
public class MapWrapperLayout extends RelativeLayout {
    /**
     * Reference to a GoogleMap object
     */
    private GoogleMap map;

    /**
     * Vertical offset in pixels between the bottom edge of our InfoWindow
     * and the marker position (by default it's bottom edge too).
     * It's a good idea to use custom markers and also the InfoWindow frame,
     * because we probably can't rely on the sizes of the default marker and frame.
     */
    private int bottomOffsetPixels;

    /**
     * A currently selected marker
     */
    private Marker marker;

    /**
     * Our custom view which is returned from either the InfoWindowAdapter.getInfoContents
     * or InfoWindowAdapter.getInfoWindow
     */
    private View infoWindow;

    public MapWrapperLayout(Context context) {
        super(context);
    }

    public MapWrapperLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MapWrapperLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * Must be called before we can route the touch events
     */
    public void init(GoogleMap map, int bottomOffsetPixels) {
        this.map = map;
        this.bottomOffsetPixels = bottomOffsetPixels;
    }

    /**
     * Best to be called from either the InfoWindowAdapter.getInfoContents
     * or InfoWindowAdapter.getInfoWindow.
     */
    public void setMarkerWithInfoWindow(Marker marker, View infoWindow) {
        this.marker = marker;
        this.infoWindow = infoWindow;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean ret = false;
        // Make sure that the infoWindow is shown and we have all the needed references
        if (marker != null && marker.isInfoWindowShown() && map != null && infoWindow != null) {
            // Get a marker position on the screen
            Point point = map.getProjection().toScreenLocation(marker.getPosition());

            // Make a copy of the MotionEvent and adjust it's location
            // so it is relative to the infoWindow left top corner
            MotionEvent copyEv = MotionEvent.obtain(ev);
            copyEv.offsetLocation(
                    -point.x + (infoWindow.getWidth() / 2),
                    -point.y + infoWindow.getHeight() + bottomOffsetPixels);

            // Dispatch the adjusted MotionEvent to the infoWindow
            ret = infoWindow.dispatchTouchEvent(copyEv);
        }
        // If the infoWindow consumed the touch event, then just return true.
        // Otherwise pass this event to the super class and return it's result
        return ret || super.dispatchTouchEvent(ev);
    }

}
