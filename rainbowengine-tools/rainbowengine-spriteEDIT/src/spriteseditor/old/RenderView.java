///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package spriteseditor.old;
//
//import the_nights.rainbow_engine.common.IScreenBuffer;
//import the_nights.rainbow_engine.core.Engine;
//import the_nights.rainbow_engine.core.EngineColors;
//import the_nights.rainbow_engine.graphics.Rectangle;
//
//
//
///**
// *
// * @author Stephanie
// */
//public class RenderView extends GUIObject{
//    private int[] pixels;
//    private Rectangle background; 
//    int xPos;
//    int yPos;
//    int width;
//    int height;
//    
//    public RenderView(SpritesEditor editor,int  xPos, int yPos,int width,int height) {
//        super(editor);
//        this.height = height;
//        this.width = width;
//        this.xPos = xPos;
//        this.yPos = yPos;
//        this.background = new Rectangle(xPos, yPos, width, height);
//        this.background.generateGrafics(EngineColors.Blue);
//        this.pixels = new int[width*height];        
//    }
////
////    @Override
////    public void render(IScreenBuffer renderHandler) {
////        render(renderHandler,1,1);
////    }
////
////    @Override
////    public void render(IScreenBuffer renderHandler, int xZoom, int yZoom) {
////        renderHandler.renderRectangle(background);
////        //renderHandler.renderPixels(pixels,xPos,yPos,width,height);
////    }
////
////    @Override
////    public void update(Engine engine) {
////    
////    }
//
//    @Override
//    public void render(IScreenBuffer renderHandler, int xZoom, int yZoom) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void update(Engine engine) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    
//}
