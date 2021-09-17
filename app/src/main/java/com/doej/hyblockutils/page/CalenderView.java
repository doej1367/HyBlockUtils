package com.doej.hyblockutils.page;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.view.View;

import com.doej.hyblockutils.main.MainActivity;
import com.doej.hyblockutils.util.HyBlockEvent;
import com.doej.hyblockutils.util.TimeConverter;

/**
 *
 * @author doej1367
 */
public class CalenderView extends View {
    private final Paint paint = new Paint();
    private int foregroundColor;
    private int textSize, spaceTop;
    private int middle;
    private Canvas canvas;

    public CalenderView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        textSize = ((MainActivity) getContext()).getCanvasTextSize();
        spaceTop = (int) Math.round(textSize * 2.5);
        middle = getWidth() / 2;
        int nightModeFlags = getContext().getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        foregroundColor = nightModeFlags == Configuration.UI_MODE_NIGHT_YES ? Color.WHITE : Color.BLACK;
        paint.setFlags(TextPaint.ANTI_ALIAS_FLAG);
        paint.setTextSize(textSize);
        paint.setColor(Color.GRAY);
        canvas.drawText("SkyBlock-Time: " + TimeConverter.getIGTime(), 0, textSize, paint);
        paint.setColor(foregroundColor);
        canvas.drawLine(middle, spaceTop, middle, 24 * textSize + spaceTop, paint);
        for (int i = 0; i < 25; i++)
            canvas.drawLine(middle - textSize / (i % 6 == 0 ? 1f : 2f), i * textSize + spaceTop, middle + textSize / (i % 6 == 0 ? 1f : 2f), i * textSize + spaceTop, paint);
        for (HyBlockEvent event : HyBlockEvent.EVENTS_ALL)
            drawEvent(event);
    }

    private void drawEvent(HyBlockEvent event) {
        int unitsPerIrlHour = 3;
        int unitsPerIrlDay = 24 * unitsPerIrlHour;
        int timeUnitsFromNow = event.getIgDaysToNextOccurrence();
        int duration = event.getDurationInIgDays();
        if (timeUnitsFromNow >= unitsPerIrlDay * 2)
            return;
        if (timeUnitsFromNow < 0) {
            duration += timeUnitsFromNow;
            timeUnitsFromNow = 0;
        }
        int x = 0;
        if (timeUnitsFromNow >= unitsPerIrlDay)
            x = middle;
        int unitOfDay = timeUnitsFromNow % unitsPerIrlDay;
        int y1 = Math.min(unitOfDay * textSize / unitsPerIrlHour + spaceTop, unitsPerIrlDay * textSize / unitsPerIrlHour + spaceTop);
        int y2 = Math.min(y1 + duration * textSize / unitsPerIrlHour, unitsPerIrlDay * textSize / unitsPerIrlHour + spaceTop);
        int yText = y1 + (duration <= 0 ? 0 : (textSize));
        int xText = x;
        paint.setColor(event.getColor());
        for (int i = 0; i < duration; i++) {
            if (timeUnitsFromNow + i >= unitsPerIrlDay * 2)
                break;
            if (timeUnitsFromNow + i >= unitsPerIrlDay)
                x = middle;
            unitOfDay = ((timeUnitsFromNow + i) < 0) ? (timeUnitsFromNow + i) : ((timeUnitsFromNow + i) % unitsPerIrlDay);
            y1 = Math.min(unitOfDay * textSize / unitsPerIrlHour + spaceTop, unitsPerIrlDay * textSize / unitsPerIrlHour + spaceTop);
            y2 = Math.min(y1 + textSize / unitsPerIrlHour, unitsPerIrlDay * textSize / unitsPerIrlHour + spaceTop);
            canvas.drawRect(x + 1, y1, x + middle, y2 + 1, paint);
        }
        canvas.drawRect(x + 1, y2 - 2, x + middle, y2, paint);
        paint.setColor(foregroundColor);
        canvas.drawText(event.getName(), xText + (spaceTop >> 2), yText - 2, paint);
    }
}
