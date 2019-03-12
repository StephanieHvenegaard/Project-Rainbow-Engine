///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package spriteseditor.old;
//
//import the_nights.rainbow_engine.core.EngineColors;
//import the_nights.rainbow_engine.graphics.DefaultScreenbuffer;
//
//
///**
// *
// * @author Stephanie
// */
//public class SEScreenBuffer extends DefaultScreenbuffer {
//
////    boolean ignoreAlfa = false;
////
////    public SEScreenBuffer(int width, int height) {
////        super(width, height);
////    }
////    @Override
////    public void setPixel(int pixel, int x, int y) {
//////        if (!ignoreAlfa) 
//////                if((pixel != EngineColors.AlphaARGB || pixel != EngineColors.AlphaRGB))
//////                {
//////        }
//////        if ((x >= getCamara().getX() && x < getCamara().getX() + getCamara().getWidth())
//////                && (y >= getCamara().getY() && y < getCamara().getY() + getCamara().getHeight())) {
//////            int pixelIndex = (x - getCamara().getX()) + (y - getCamara().getY()) * getView().getWidth();
//////            if (pixelIndex < getPixels().length) {
//////                getPixels()[pixelIndex] = pixel;
//////            }
//////        }
////if (pixel != EngineColors.AlphaARGB) {
////            if (pixel != EngineColors.AlphaRGB) {
////                
////                if ((x >= camara.getX() && x < camara.getX() + camara.getWidth())
////                        && (y >= camara.getY() && y < camara.getY() + camara.getHeight())) {
////                    int pixelIndex = (x - camara.getX()) + (y - camara.getY()) * view.getWidth();
////                    if (pixelIndex < pixels.length) {
////                        pixels[pixelIndex] = pixel;
////                    }
////                }
////            }
////        }
////    }
////
////    public boolean isIgnoreAlfa() {
////        return ignoreAlfa;
////    }
////
////    public void setIgnoreAlfa(boolean ignoreAlfa) {
////        this.ignoreAlfa = ignoreAlfa;
////    }
//
//    public SEScreenBuffer(int width, int height) {
//        super(width, height);
//    }
//
////    boolean ignoreAlfa = false;
////
////    public SEScreenBuffer(int width, int height) {
////        super(width, height);
////    }
////    @Override
////    public void setPixel(int pixel, int x, int y) {
//////        if (!ignoreAlfa) 
//////                if((pixel != EngineColors.AlphaARGB || pixel != EngineColors.AlphaRGB))
//////                {
//////        }
//////        if ((x >= getCamara().getX() && x < getCamara().getX() + getCamara().getWidth())
//////                && (y >= getCamara().getY() && y < getCamara().getY() + getCamara().getHeight())) {
//////            int pixelIndex = (x - getCamara().getX()) + (y - getCamara().getY()) * getView().getWidth();
//////            if (pixelIndex < getPixels().length) {
//////                getPixels()[pixelIndex] = pixel;
//////            }
//////        }
////if (pixel != EngineColors.AlphaARGB) {
////            if (pixel != EngineColors.AlphaRGB) {
////                
////                if ((x >= camara.getX() && x < camara.getX() + camara.getWidth())
////                        && (y >= camara.getY() && y < camara.getY() + camara.getHeight())) {
////                    int pixelIndex = (x - camara.getX()) + (y - camara.getY()) * view.getWidth();
////                    if (pixelIndex < pixels.length) {
////                        pixels[pixelIndex] = pixel;
////                    }
////                }
////            }
////        }
////    }
////
////    public boolean isIgnoreAlfa() {
////        return ignoreAlfa;
////    }
////
////    public void setIgnoreAlfa(boolean ignoreAlfa) {
////        this.ignoreAlfa = ignoreAlfa;
////    }
//
//}
