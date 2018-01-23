package sophistry.weoweogame.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import sophistry.weoweogame.R;

/**
 * Created by Vincent on 1/22/2018.
 */

public class TTTBoardView extends View {

    private Paint linePaint;
    private TextPaint paintForO;
    private TextPaint paintForX;

    public TTTBoardView(Context context) {
        super(context);
    }

    public TTTBoardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TTTBoardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        initLinePaint();
        initTextPaint();
    }

    private void initLinePaint() {
        linePaint = new Paint();
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(1);
        linePaint.setAntiAlias(true);
        linePaint.setColor(getResources().getColor(R.color.colorTicTacToeGrid));
    }

    private void initTextPaint() {
        float scaledXOSizeInPixels = getResources().getDimensionPixelSize(R.dimen.font_size_XO);
        Typeface typefaceForXO = Typeface.createFromAsset(getResources().getAssets(), "fonts/Roboto-Black.ttf");

        paintForO = new TextPaint();
        paintForO.setColor(getResources().getColor(R.color.colorO));
        paintForO.setTextSize(scaledXOSizeInPixels);
        paintForO.setTypeface(typefaceForXO);

        paintForX = new TextPaint();
        paintForX.setColor(getResources().getColor(R.color.colorX));
        paintForX.setTextSize(scaledXOSizeInPixels);
        paintForX.setTypeface(typefaceForXO);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
