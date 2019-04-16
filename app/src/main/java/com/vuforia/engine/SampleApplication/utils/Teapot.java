/*===============================================================================
Copyright (c) 2016-2018 PTC Inc. All Rights Reserved.

Copyright (c) 2012-2014 Qualcomm Connected Experiences, Inc. All Rights Reserved.

Vuforia is a trademark of PTC Inc., registered in the United States and other 
countries.
===============================================================================*/

package com.vuforia.engine.SampleApplication.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import de.javagl.obj.Obj;
import de.javagl.obj.ObjData;
import de.javagl.obj.ObjReader;
import de.javagl.obj.ObjUtils;

/**
 * This class contains all the information needed to augment a teapot model
 */
public class Teapot extends MeshObject {
    private Buffer mVertBuff;
    private Buffer mTexCoordBuff;
    private Buffer mNormBuff;
    private Buffer mIndBuff;
    private Context context;

    private int indicesNumber = 0;
    private int verticesNumber = 0;


    public Teapot(Context context) {
        Obj obj = null;
        // Read an OBJ file
        InputStream objInputStream = null;
        this.context=context;
        try {
            InputStream is = context.getAssets().open("Models/untitled.obj");
////            String file = "./apple.obj";
////            Log.e("MERDE", input);
            objInputStream = is;
            obj = ObjReader.read(objInputStream);
            Log.e("MERDE", "TRY");
        } catch (FileNotFoundException e) {
            Log.e("MERDE", "CATCH 1");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("MERDE", "CATCH 2");
            e.printStackTrace();
        }

//        // Prepare the Obj so that its structure is suitable for
//        // rendering with OpenGL:
//        // 1. Triangulate it
//        // 2. Make sure that texture coordinates are not ambiguous
//        // 3. Make sure that normals are not ambiguous
//        // 4. Convert it to single-indexed data
        obj = ObjUtils.convertToRenderable(obj);

        // Obtain the data from the OBJ, as direct buffers:
        mIndBuff = ObjData.getFaceVertexIndices(obj, 3);
        mVertBuff = ObjData.getVertices(obj);
        mTexCoordBuff = ObjData.getTexCoords(obj, 2);
        mNormBuff = ObjData.getNormals(obj);

//        setVerts();
//        setTexCoords();
//        setNorms();
//        setIndices();
    }


//    private void setVerts() {
//        double[] TEAPOT_VERTS = {
//                1.0,-1.0,-1.0,
//                1.0,-1.0,1.0,
//                -1.0,-1.0,1.0,
//                -1.0,-1.0,-1.0,
//                1.0,1.0,-0.999999,
//                0.999999,1.0,1.000001,
//                -1.0,1.0,1.0,
//                -1.0,1.0,-1.0,
//                1.0,-1.0,-1.0,
//                1.0,1.0,-0.999999,
//                0.999999,1.0,1.000001,
//                1.0,-1.0,-1.0,
//                0.999999,1.0,1.000001,
//                1.0,-1.0,1.0,
//                1.0,-1.0,1.0,
//                0.999999,1.0,1.000001,
//                -1.0,1.0,1.0,
//                1.0,-1.0,1.0,
//                -1.0,1.0,1.0,
//                -1.0,-1.0,1.0,
//                -1.0,-1.0,1.0,
//                -1.0,1.0,1.0,
//                -1.0,1.0,-1.0,
//                -1.0,-1.0,1.0,
//                -1.0,1.0,-1.0,
//                -1.0,-1.0,-1.0,
//                1.0,1.0,-0.999999,
//                1.0,-1.0,-1.0,
//                -1.0,-1.0,-1.0,
//                1.0,1.0,-0.999999,
//                -1.0,-1.0,-1.0,
//                -1.0,1.0,-1.0
//        };
//        mVertBuff = fillBuffer(TEAPOT_VERTS);
//        verticesNumber = TEAPOT_VERTS.length / 3;
//    }
//
//
//    private void setTexCoords() {
//        double[] TEAPOT_TEX_COORDS = {
//                0.0,0.0,
//                1.0,0.0,
//                1.0,1.0,
//                0.0,1.0,
//                0.0,0.0,
//                0.0,1.0,
//                1.0,1.0,
//                1.0,0.0,
//                1.0,0.0,
//                1.0,1.0,
//                1.0,1.0,
//                0.0,1.0,
//                0.0,0.0,
//                1.0,0.0,
//                0.0,0.0,
//                0.0,1.0,
//                0.0,0.0,
//                1.0,0.0,
//                1.0,1.0,
//                0.0,0.0,
//                1.0,1.0,
//                1.0,0.0,
//                1.0,1.0,
//                1.0,1.0,
//                0.0,1.0,
//                0.0,0.0,
//                0.0,0.0,
//                1.0,1.0,
//                1.0,1.0,
//                0.0,1.0,
//                0.0,0.0,
//                0.0,0.0,
//        };
//        mTexCoordBuff = fillBuffer(TEAPOT_TEX_COORDS);
//    }
//
//
//    private void setNorms() {
//        double[] TEAPOT_NORMS = {
//                0.0,-1.0,0.0,
//                0.0,-1.0,0.0,
//                0.0,-1.0,0.0,
//                0.0,-1.0,0.0,
//                0.0,1.0,0.0,
//                0.0,1.0,0.0,
//                0.0,1.0,0.0,
//                0.0,1.0,0.0,
//                1.0,0.0,0.0,
//                1.0,0.0,0.0,
//                1.0,0.0,0.0,
//                1.0,0.0,0.0,
//                1.0,0.0,0.0,
//                1.0,0.0,0.0,
//                -0.0,-0.0,1.0,
//                -0.0,-0.0,1.0,
//                -0.0,-0.0,1.0,
//                -0.0,-0.0,1.0,
//                -0.0,-0.0,1.0,
//                -0.0,-0.0,1.0,
//                -1.0,-0.0,-0.0,
//                -1.0,-0.0,-0.0,
//                -1.0,-0.0,-0.0,
//                -1.0,-0.0,-0.0,
//                -1.0,-0.0,-0.0,
//                -1.0,-0.0,-0.0,
//                0.0,0.0,-1.0,
//                0.0,0.0,-1.0,
//                0.0,0.0,-1.0,
//                0.0,0.0,-1.0,
//                0.0,0.0,-1.0,
//                0.0,0.0,-1.0
//        };
//        mNormBuff = fillBuffer(TEAPOT_NORMS);
//    }
//
//
//    private void setIndices() {
//        short[] TEAPOT_INDICES = {
//        };
//
//        mIndBuff = fillBuffer(TEAPOT_INDICES);
//        indicesNumber = TEAPOT_INDICES.length;
//    }


    public int getNumObjectIndex() {
        return indicesNumber;
    }


    @Override
    public int getNumObjectVertex() {
        return verticesNumber;
    }

    @Override
    public Buffer getBuffer(BUFFER_TYPE bufferType) {
        Buffer result = null;
        switch (bufferType) {
            case BUFFER_TYPE_VERTEX:
                result = mVertBuff;
                Log.e("MERDE", "buffer_type_vertex");
                break;
            case BUFFER_TYPE_TEXTURE_COORD:
                result = mTexCoordBuff;
                Log.e("MERDE", "buffer_type_texture_coord");

                break;
            case BUFFER_TYPE_NORMALS:
                result = mNormBuff;
                Log.e("MERDE", "buffer_type_normals");

                break;
            case BUFFER_TYPE_INDICES:
                result = mIndBuff;
                Log.e("MERDE", "buffer_type_indice");

            default:
                break;

        }
        return result;
    }

}
