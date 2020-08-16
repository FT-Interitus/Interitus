package de.ft.interitus.UI.UIElements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import de.ft.interitus.ProgrammingSpace;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIElements.check.CheckMouse;

public class QuickInfo {
    private String text;
    private int x;
    private int y;
    private BitmapFont font;
    private GlyphLayout glyphLayout;
    private boolean shown=false;
    private Color backgroundColor=new Color(0.5f  ,0.5f ,0.5f ,1.0f );
    private Color textColor=new Color(0.0f,0.0f,0.0f,1.0f);
    private int abstandvonrand=5;
    private int eckenradius=5;
    private boolean attachedToMouse=false;

    private float animationsAlphaPosition=0;
    private float alphaMax=1;
    private float alphaMin=0;

    private float fadeInSpeed=100;
    private float fadeOutSpeed=50;

    private boolean doonce=false;
    private long zeitstempel;
    private Rectangle selfCheckRectangle= new Rectangle();
    private boolean selfCheck=false;




    public QuickInfo(int x, int y, String text) {
        this.x = x;
        this.y = y;
        this.text = text;
        glyphLayout=new GlyphLayout();
        font = new BitmapFont();
    }

    public void RectangleSelfCheck(){
        if (CheckMouse.isMouseover(selfCheckRectangle)) {

            if (doonce) {
                zeitstempel = System.currentTimeMillis() + 2000;
                doonce = false;
            }
            if (zeitstempel < System.currentTimeMillis()) {
                setX(Gdx.input.getX());
                setY(Gdx.graphics.getHeight()-Gdx.input.getY());
                fadeIn();
            }
        } else {
            if (!doonce) {
                doonce = true;
                fadeOut();

            }

        }
    }

    /**
     * draws and updates the QuickInfo
     */
    public void update(){
        if(selfCheck)RectangleSelfCheck();

        if(attachedToMouse){
            this.x=Gdx.input.getX();
            this.y=Gdx.graphics.getHeight()-Gdx.input.getY();
        }

        if(shown){
            if(animationsAlphaPosition<=alphaMax-alphaMax/fadeInSpeed){
                animationsAlphaPosition+=alphaMax/fadeInSpeed;
            }
        }else if(!shown){
            if(animationsAlphaPosition>=alphaMin+alphaMax/fadeInSpeed){
                animationsAlphaPosition-=alphaMax/fadeOutSpeed;
            }
        }
        this.glyphLayout.setText(this.font,this.text);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA); //Enalble Alpha Rendering
        ProgrammingSpace.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        backgroundColor.a=animationsAlphaPosition;
        ProgrammingSpace.shapeRenderer.setColor(backgroundColor);
        ProgrammingSpace.shapeRenderer.roundendrect(this.x,this.y,glyphLayout.width+abstandvonrand*2,glyphLayout.height+abstandvonrand*2,eckenradius);
        ProgrammingSpace.shapeRenderer.end();

        ProgrammingSpace.batch.begin();
        textColor.a=animationsAlphaPosition;
        font.setColor(textColor);
        font.draw(ProgrammingSpace.batch,glyphLayout,this.x+abstandvonrand,this.y+glyphLayout.height+abstandvonrand);
        ProgrammingSpace.batch.end();
    }

    /**
     * tells the QuickInfo to fade in
     */
    public void fadeIn() {
        if(!shown){
            shown=true;
            //...
        }
    }

    /**
     * tells the QuickInfo to fade out
     */
    public void fadeOut(){
        if(shown){
            shown=false;
            //...
        }
    }

    public String getText() {
        return text;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public BitmapFont getFont() {
        return font;
    }

    public GlyphLayout getGlyphLayout() {
        return glyphLayout;
    }

    public boolean isShown() {
        return shown;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public Color getTextColor() {
        return textColor;
    }

    public int getAbstandvonrand() {
        return abstandvonrand;
    }

    public int getEckenradius() {
        return eckenradius;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setFont(BitmapFont font) {
        this.font = font;
    }

    public void setGlyphLayout(GlyphLayout glyphLayout) {
        this.glyphLayout = glyphLayout;
    }

    public void setShown(boolean shown) {
        this.shown = shown;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public void setAbstandvonrand(int abstandvonrand) {
        this.abstandvonrand = abstandvonrand;
    }

    public void setEckenradius(int eckenradius) {
        this.eckenradius = eckenradius;
    }

    public float getAnimationsAlphaPosition() {
        return animationsAlphaPosition;
    }

    public float getAlphaMax() {
        return alphaMax;
    }

    public float getAlphaMin() {
        return alphaMin;
    }

    public float getFadeInSpeed() {
        return fadeInSpeed;
    }

    public float getFadeOutSpeed() {
        return fadeOutSpeed;
    }

    public void setAlphaMax(float alphaMax) {
        this.alphaMax = alphaMax;
    }

    public void setAlphaMin(float alphaMin) {
        this.alphaMin = alphaMin;
    }

    public void setFadeInSpeed(float fadeInSpeed) {
        this.fadeInSpeed = fadeInSpeed;
    }

    public void setFadeOutSpeed(float fadeOutSpeed) {
        this.fadeOutSpeed = fadeOutSpeed;
    }

    public boolean isAttachedToMouse() {
        return attachedToMouse;
    }

    public void setAttachedToMouse(boolean attachedToMouse) {
        this.attachedToMouse = attachedToMouse;
    }

    public Rectangle getSelfCheckRectangle() {
        return selfCheckRectangle;
    }

    public boolean isSelfCheck() {
        return selfCheck;
    }

    public void setSelfCheckRectangle(Rectangle selfCheckRectangle) {
        this.selfCheckRectangle = selfCheckRectangle;
    }

    public void setSelfCheck(boolean selfCheck) {
        this.selfCheck = selfCheck;
    }

    public boolean isDoonce() {
        return doonce;
    }

    public long getZeitstempel() {
        return zeitstempel;
    }

    public void setDoonce(boolean doonce) {
        this.doonce = doonce;
    }

    public void setZeitstempel(long zeitstempel) {
        this.zeitstempel = zeitstempel;
    }
}


