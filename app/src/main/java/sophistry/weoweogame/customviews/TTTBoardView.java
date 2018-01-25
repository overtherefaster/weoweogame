package sophistry.weoweogame.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.util.Pair;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import sophistry.weoweogame.R;
import sophistry.weoweogame.data.Cell;
import sophistry.weoweogame.data.MarkType;

/**
 * Created by Vincent on 1/22/2018.
 */

public class TTTBoardView extends View {

    private Paint linePaint;
    private Paint highlightedCellPaint;
    private TextPaint paintForO;
    private TextPaint paintForX;

    private final int TOTAL_CELLS = 9;
    private int[][] board = new int[3][3];
    private Rect[] cells = new Rect[TOTAL_CELLS];

    private final float X_PARTITION_FACTOR = 1/3F;
    private final float Y_PARTITION_FACTOR = 1/3F;

    private boolean isTouched = false;
    private boolean pendingX = false;
    private boolean pendingO = false;
    private int touchedCell;

    public TTTBoardView(Context context) {
        this(context, null);
    }

    public TTTBoardView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TTTBoardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        initLinePaint();
        initHighlightedCellPaint();
        initTextPaint();
    }

    private void initLinePaint() {
        linePaint = new Paint();
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(2);
        linePaint.setAntiAlias(true);
        linePaint.setColor(getResources().getColor(R.color.colorTicTacToeGrid));
    }

    private void initHighlightedCellPaint() {
        highlightedCellPaint = new Paint();
        highlightedCellPaint.setStyle(Paint.Style.FILL);
        highlightedCellPaint.setAntiAlias(true);
        highlightedCellPaint.setColor(getResources().getColor(R.color.colorHighlightedCell));
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

    private void initCells() {

        float cellWidth = getWidth() * X_PARTITION_FACTOR;
        float cellHeight = getHeight() * Y_PARTITION_FACTOR;

        int row, col;

        for (int i = 0; i < cells.length; i++) {
            row = i / 3;
            col = i % 3;
            cells[i] = new Rect(
                    (int)(col * cellWidth), // left
                    (int)(row * cellHeight), // top
                    (int)((col + 1) * cellWidth), // right
                    (int)((row + 1) * cellHeight)); // bottom
        }
    }

    private void drawVerticalLines(Canvas canvas) {
        canvas.drawLine(X_PARTITION_FACTOR * getWidth(), 0, X_PARTITION_FACTOR * getWidth(), getHeight(), linePaint);
        canvas.drawLine(getWidth() * X_PARTITION_FACTOR * 2, 0, getWidth() * X_PARTITION_FACTOR * 2, getHeight(), linePaint);
    }

    private void drawHorizontalLines(Canvas canvas) {
        canvas.drawLine(0, getHeight() * Y_PARTITION_FACTOR, getWidth(), getHeight() * Y_PARTITION_FACTOR, linePaint);
        canvas.drawLine(0, getHeight() * Y_PARTITION_FACTOR * 2, getWidth(), getHeight() * Y_PARTITION_FACTOR * 2, linePaint);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // Constrain board ratio to be a square
        int length = Math.min(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
        setMeasuredDimension(length, length);

        /*
        int width, height;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        // determine width
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            width = Math.min(desiredWidth, widthSize);
        } else {
            width = desiredWidth;
        }

        // determine height
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            height = Math.min(desiredHeight, heightSize);
        } else {
            height = desiredHeight;
        }
        */

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initCells();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawVerticalLines(canvas);
        drawHorizontalLines(canvas);

        if (isTouched) {
            drawHighlightedCell(canvas);
        }

        if (pendingO) {
            drawO(canvas);
        }

        if (pendingX) {
            drawX(canvas);
        }

    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    // touch event
    // find which cell
    // DOWN -> highlight grey (if empty)
    // UP act according to state (cell state ~> which player's turn it is)


    private int getCellIndexFromXY (int x, int y) {

        for (int i = 0; i < cells.length; i++) {
            if (cells[i].contains(x, y)) {
                return i;
            }
        }
        return -1;
    }

    private int getCellIndexFromRowCol (int row, int col) {
        return (row * 3) + col;
    }

    public Pair<Integer,Integer> getCellRowCol (int x, int y) {
        int idx = getCellIndexFromXY (x, y);
        return new Pair<>(idx/3, idx%3);
    }

    public boolean withinBoardBounds (int x, int y) {
        return (x >= 0 && x <= getWidth() &&
                y >= 0 && y <= getHeight());
    }

    private void drawO (Canvas canvas) {
        canvas.drawText("O", cells[touchedCell].exactCenterX(), cells[touchedCell].exactCenterY(), paintForO );
        pendingO = false;
    }

    private void drawX (Canvas canvas) {
        canvas.drawText("X", cells[touchedCell].exactCenterX(), cells[touchedCell].exactCenterY(), paintForX );
        pendingX = false;
    }


    public void markXO(boolean isO, int row, int col) {
        int cellIndex = getCellIndexFromRowCol(row, col);

        if (isO) {
            pendingO = true;
        }
        else {
            pendingX = true;
        }
        touchedCell = cellIndex;
        invalidate(cells[cellIndex]);
    }

    public void highlightCell (int row, int col) {
        int cellIndex = getCellIndexFromRowCol(row, col);

        isTouched = true;
        touchedCell = cellIndex;
        invalidate(cells[cellIndex]);

    }

    public void unhighlightCell (int row, int col) {
        int cellIndex = getCellIndexFromRowCol(row, col);

        isTouched = false;
        invalidate(cells[cellIndex]);
    }

    public void cancelHighlight () {
        isTouched = false;
        invalidate();
    }

    private void drawHighlightedCell (Canvas canvas) {
        //System.out.println ("touchedcell = " + touchedCell);
        //Log.d ("", "[vc_touchedcell]="+touchedCell);
        canvas.drawRect(cells[touchedCell], highlightedCellPaint);
    }




}
