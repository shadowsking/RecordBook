package com.example.king.recordbook.api;

import com.example.king.recordbook.api.model.Group.GroupModel;
import com.example.king.recordbook.api.model.Point.PointModel;
import com.example.king.recordbook.api.model.Result.ResultModel;
import com.example.king.recordbook.api.model.Student.StudentModel;
import com.example.king.recordbook.api.model.Token;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by KING on 02.02.2018.
 */

public interface ApiService {
    @Multipart
    @POST("login")
    Call<Token> getToken(@PartMap Map<String, RequestBody> params);

    @GET("isValidToken")
    Call<Token> isValidToken(@Query("token") String  token);

    @GET("getGroup")
    Observable<ArrayList<GroupModel>> getGrouprx(@Query("token") String  token);

    @Multipart
    @POST("newGroup")
    Observable<Object> addNewGroup(@PartMap Map<String, RequestBody> params);

    @GET("getStudents")
    Observable<ArrayList<StudentModel>> getStudentsRx(@Query("token") String  token, @Query("id_group") int id_group);

    @Multipart
    @POST("newStudent")
    Observable<Object> addNewStudentRx(@PartMap Map<String, RequestBody> params);
    /*
    @GET("getPoint")
    Observable<Map<String, ArrayList<PointModel>>> getPoint();*/
    @GET("getAllPoint")
    Observable<ArrayList<PointModel>> getPointRx();

    @GET("getGroupResults")
    Observable<ArrayList<ResultModel>> getGroupResults(@Query("token") String  token,
                                                       @Query("id_group") int id_group,
                                                       @Query("id_point") int id_point,
                                                       @Query("semester") int semester);
    @GET("getStudentResults")
    Observable<ArrayList<ResultModel>> getStudentResults(@Query("token") String  token,
                                                       @Query("id_student") int id_student,
                                                       @Query("semester") int semester);
    @GET("getStudentResults")
    Observable<ArrayList<ResultModel>> getStudentPointResults(@Query("token") String  token,
                                                         @Query("id_student") int id_student,
                                                         @Query("id_point") int id_point);
    @GET("getDate")
    Observable<String> getCurrentDate();

    @Multipart
    @POST("newResult")
    Observable<Object> newResult(@PartMap Map<String, RequestBody> params);
}
