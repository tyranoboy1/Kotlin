package com.ji.mysolelife.contentsList

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.ji.mysolelife.R
import com.ji.mysolelife.utils.FBAuth
import com.ji.mysolelife.utils.FBRef

class ContentListActivity : AppCompatActivity() {

    lateinit var myRef : DatabaseReference

    val bookmarkIdList = mutableListOf<String>()

    lateinit var rvAdapter: ContentRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_list)

        val items = ArrayList<ContentModel>()
        val itemKeyList = ArrayList<String>()
        rvAdapter = ContentRVAdapter(baseContext, items, itemKeyList, bookmarkIdList)

        val database = Firebase.database

        val category = intent.getStringExtra("category")

        if(category == "category1"){

            myRef = database.getReference("contents")



        } else if(category == "category2"){

            myRef = database.getReference("contents2")
        }
        else if(category == "category3"){
            myRef = database.getReference("contents3")
        }
        else if(category == "category4"){
            myRef = database.getReference("contents4")
        }
        else if(category == "category5"){
            myRef = database.getReference("contents5")
        }
        else if(category == "category6"){
            myRef = database.getReference("contents6")
        }
        else if(category == "category7"){
            myRef = database.getReference("contents7")
        }
        else if(category == "category8"){
            myRef = database.getReference("contents8")
        }
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for(dataModel in dataSnapshot.children){
                    Log.d("ContentListActivity",dataModel.toString())
                    Log.d("ContentListActivity", dataModel.key.toString())
                    val item = dataModel.getValue(ContentModel::class.java)
                    items.add(item!!)
                    itemKeyList.add(dataModel.key.toString())
                }
                rvAdapter.notifyDataSetChanged()
                Log.d("ContentListActivity",items.toString())
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("ContentListActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }
        myRef.addValueEventListener(postListener)





        val rv : RecyclerView = findViewById(R.id.rv)

        rv.adapter = rvAdapter

        rv.layoutManager = GridLayoutManager(this, 2)

        getBookmarkData()

//                val myRef = database.getReference("contents8")
//        myRef.push().setValue(
//            ContentModel("사람인", "https://saraminhr.co.kr/resources/image/og.png", "https://www.saramin.co.kr/zf_user/")
//        )
//
//        myRef.push().setValue(
//            ContentModel("알바몬", "https://blog.kakaocdn.net/dn/cJIMO1/btq6BEqAjvR/HqUrzxH25DkFY7q2AyWhb1/img.png", "https://www.albamon.com/?utm_source=google&utm_medium=cpc&utm_campaign=000.%EB%B8%8C%EB%9E%9C%EB%93%9C&utm_content=000.%EB%B8%8C%EB%9E%9C%EB%93%9C&utm_term=%EC%95%8C%EB%B0%94%EB%AA%AC&gclid=CjwKCAjw79iaBhAJEiwAPYwoCLDEpy8-ZRnkbpmDI-KvH0qRNcPC1piQ9IE70O3NzXeFZggU63cBjhoCsCYQAvD_BwE")
//        )
//
//        myRef.push().setValue(
//            ContentModel("블라인드", "https://contents.nextunicorn.kr/pressRelease/8c673a6b247d56b3/58eb1798cf6db58g180899f195c3f3d27c10.png", "https://www.teamblind.com/kr/")
//        )
//        myRef.push().setValue(
//            ContentModel("잡코리아", "https://blog.kakaocdn.net/dn/RqNDg/btqDrk1MzWD/kLmQ9237BXC202Whri86Kk/img.jpg", "https://www.jobkorea.co.kr/")
//        )


        //                val myRef = database.getReference("contents5")
//        myRef.push().setValue(
//            ContentModel("홈트(가슴)", "https://image.utoimage.com/preview/cp872722/2020/07/202007029985_500.jpg", "https://esquirekorea.co.kr/article/45485")
//        )
//        myRef.push().setValue(
//            ContentModel("홈트(복근)", "https://www.k-health.com/news/photo/201808/37515_25231_1858.jpg", "https://brunch.co.kr/@tenbody/3897")
//        )
//        myRef.push().setValue(
//            ContentModel("홈트(어깨)", "https://is5-ssl.mzstatic.com/image/thumb/Purple30/v4/03/f7/35/03f73572-0205-d43e-0f00-1e48fbfbc926/source/512x512bb.jpg", "https://blog.happydong.kr/1023")
//        )
//        myRef.push().setValue(
//            ContentModel("홈트(하체)", "https://cdn.mkhealth.co.kr/news/photo/202205/57860_60965_4145.jpg", "https://jooaoo.tistory.com/entry/%ED%8A%BC%ED%8A%BC%ED%95%9C-%ED%95%98%EC%B2%B4%EB%A5%BC-%EC%9C%84%ED%95%9C-%EC%A7%91%EC%97%90%EC%84%9C-%ED%95%A0%EC%88%98-%EC%9E%88%EB%8A%94-%ED%95%98%EC%B2%B4%EC%9A%B4%EB%8F%99-%ED%95%98%EC%B2%B4%EB%A3%A8%ED%8B%B4-%ED%99%88%ED%8A%B8%EC%9A%B4%EB%8F%99%EB%A3%A8%ED%8B%B4")
//        )
//        myRef.push().setValue(
//            ContentModel("홈트(등)", "https://blog.kakaocdn.net/dn/eqRpKv/btq33aZutpA/zdFu34EX5aTh0gzHi3AC1k/img.jpg", "https://dietwill.tistory.com/entry/%EB%82%A8%EC%9E%90-%EB%93%B1%EC%9A%B4%EB%8F%99-%EB%A3%A8%ED%8B%B4-%EC%8A%88%ED%8D%BC%EB%A7%A8-%EC%9E%90%EC%84%B8%EC%97%90%EC%84%9C-%ED%95%98%EB%8A%94-%EB%A7%A8%EB%AA%B8%EC%9A%B4%EB%8F%99-6%EA%B0%80%EC%A7%80-%EA%B8%B0%EA%B5%AC-%EC%97%86%EC%96%B4%EB%8F%84-%EC%9E%90%EA%B7%B9-%EC%9E%98%EB%A8%B9%EC%96%B4%EC%9A%94")
//        )

//        myRef.push().setValue(
//            ContentModel("법률", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2F65ZTt%2Fbtq1ULoc5fh%2FqKzxbbLUkMlGsrGwy43dK1%2Fimg.png", "https://yul2house.tistory.com/201?category=972562")
//        )
//        myRef.push().setValue(
//            ContentModel("통신", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbioiZh%2Fbtq5aApgYm8%2FCMJFZl2BWIKvPafYTQFdZK%2Fimg.jpg", "https://yul2house.tistory.com/238?category=972562")
//        )
//        myRef.push().setValue(
//            ContentModel("어플", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcjkHHE%2Fbtq7UNTcYfQ%2FmghGl9fNQ5ZW8Usd6O8FiK%2Fimg.jpg", "https://yul2house.tistory.com/288?category=972562")
//        )
//        myRef.push().setValue(
//            ContentModel("세금", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FSnpQW%2Fbtrjvr0WBZI%2F4DzoCLpRurVV9ecabzkHh1%2Fimg.png", "https://yul2house.tistory.com/422?category=972562")


//                val myRef3 = database.getReference("contents3")
//        myRef3.push().setValue(
//            ContentModel("이사할때필수사이트", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbCBqZG%2Fbtq5zFYmzgO%2FHfDxhfzGEsX2jAKASJK0f0%2Fimg.jpg", "https://yul2house.tistory.com/248")
//        )




//        val myRef2 = database.getReference("contents2")
//        myRef2.push().setValue(
//            ContentModel("리코타치즈", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FblYPPY%2Fbtq66v0S4wu%2FRmuhpkXUO4FOcrlOmVG4G1%2Fimg.png", "https://philosopher-chan.tistory.com/1235?category=941578")
//        )
//
//        myRef2.push().setValue(
//            ContentModel("황금노른짜장", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FznKK4%2Fbtq665AUWem%2FRUawPn5Wwb4cQ8BetEwN40%2Fimg.png", "https://philosopher-chan.tistory.com/1236?category=941578")
//        )
//
//        myRef2.push().setValue(
//           ContentModel("사골곰탕파스타", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fbtig9C%2Fbtq65UGxyWI%2FPRBIGUKJ4rjMkI7KTGrxtK%2Fimg.png", "https://philosopher-chan.tistory.com/1237?category=941578")
//        )
//
//        myRef2.push().setValue(
//            ContentModel("아웃백투움바파스타", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcOYyBM%2Fbtq67Or43WW%2F17lZ3tKajnNwGPSCLtfnE1%2Fimg.png", "https://philosopher-chan.tistory.com/1238?category=941578")
//        )
//                myRef2.push().setValue(
//            ContentModel("당면찜닭", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fekn5wI%2Fbtq66UlN4bC%2F8NEzlyot7HT4PcjbdYAINk%2Fimg.png", "https://philosopher-chan.tistory.com/1239?category=941578")
//        )
//                myRef2.push().setValue(
//            ContentModel("스팸부대국수", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2F123LP%2Fbtq65qy4hAd%2F6dgpC13wgrdsnHigepoVT1%2Fimg.png", "https://philosopher-chan.tistory.com/1240?category=941578")
//        )
//                myRef2.push().setValue(
//            ContentModel("불닭팽이버섯", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fl2KC3%2Fbtq64lkUJIN%2FeSwUPyQOddzcj6OAkPKZuk%2Fimg.png","https://philosopher-chan.tistory.com/1241?category=941578")
//        )
//                myRef2.push().setValue(
//            ContentModel("꿀호떡샌드위치", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FmBh5u%2Fbtq651yYxop%2FX3idRXeJ0VQEoT1d6Hln30%2Fimg.png", "https://philosopher-chan.tistory.com/1242?category=941578")
//        )
//                myRef2.push().setValue(
//            ContentModel("굽네치킨", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FlOnja%2Fbtq69Tmp7X4%2FoUvdIEteFbq4Z0ZtgCd4p0%2Fimg.png", "https://philosopher-chan.tistory.com/1243?category=941578")
//        )
//                myRef2.push().setValue(
//           ContentModel("JMT 홈베이킹", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FNNrYR%2Fbtq64wsW5VN%2FqIaAsfmFtcvh4Bketug9m0%2Fimg.png", "https://philosopher-chan.tistory.com/1244?category=941578")
//        )
//                myRef2.push().setValue(
//           ContentModel("양념장", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FK917N%2Fbtq64SP5gxj%2FNzsfNAykamW7qv1hdusp1K%2Fimg.png", "https://philosopher-chan.tistory.com/1245?category=941578")
//        )
//                myRef2.push().setValue(
//           ContentModel("디톡스주스", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FeEO4sy%2Fbtq69SgK8L3%2FttCUxYHx9aPNebNwkPcI21%2Fimg.png", "https://philosopher-chan.tistory.com/1246?category=941578")
//        )
//                myRef2.push().setValue(
//           ContentModel("도시락", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbdIKDG%2Fbtq64M96JFa%2FKcJiYgKuwKuP3fIyviXm90%2Fimg.png", "https://philosopher-chan.tistory.com/1247?category=941578")
//        )
//                myRef2.push().setValue(
//           ContentModel("참치맛나니", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FFtY3t%2Fbtq65q6P4Zr%2FWe64GM8KzHAlGE3xQ2nDjk%2Fimg.png", "https://philosopher-chan.tistory.com/1248?category=941578")
//        )
//                myRef2.push().setValue(
//          ContentModel("간장볶음면", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FOtaMq%2Fbtq67OMpk4W%2FH1cd0mda3n2wNWgVL9Dqy0%2Fimg.png", "https://philosopher-chan.tistory.com/1249?category=941578")
//        )


//        items.add(ContentModel("리코타치즈", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FblYPPY%2Fbtq66v0S4wu%2FRmuhpkXUO4FOcrlOmVG4G1%2Fimg.png", "https://philosopher-chan.tistory.com/1235?category=941578"))
//        items.add(ContentModel("황금노른짜장", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FznKK4%2Fbtq665AUWem%2FRUawPn5Wwb4cQ8BetEwN40%2Fimg.png", "https://philosopher-chan.tistory.com/1236?category=941578"))
//        items.add(ContentModel("사골곰탕파스타", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fbtig9C%2Fbtq65UGxyWI%2FPRBIGUKJ4rjMkI7KTGrxtK%2Fimg.png", "https://philosopher-chan.tistory.com/1237?category=941578"))
//        items.add(ContentModel("아웃백투움바파스타", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcOYyBM%2Fbtq67Or43WW%2F17lZ3tKajnNwGPSCLtfnE1%2Fimg.png", "https://philosopher-chan.tistory.com/1238?category=941578"))
//        items.add(ContentModel("당면찜닭", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fekn5wI%2Fbtq66UlN4bC%2F8NEzlyot7HT4PcjbdYAINk%2Fimg.png", "https://philosopher-chan.tistory.com/1239?category=941578"))
//        items.add(ContentModel("스팸부대국수", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2F123LP%2Fbtq65qy4hAd%2F6dgpC13wgrdsnHigepoVT1%2Fimg.png", "https://philosopher-chan.tistory.com/1240?category=941578"))
//        items.add(ContentModel("불닭팽이버섯", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fl2KC3%2Fbtq64lkUJIN%2FeSwUPyQOddzcj6OAkPKZuk%2Fimg.png","https://philosopher-chan.tistory.com/1241?category=941578"))
//        items.add(ContentModel("꿀호떡샌드위치", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FmBh5u%2Fbtq651yYxop%2FX3idRXeJ0VQEoT1d6Hln30%2Fimg.png", "https://philosopher-chan.tistory.com/1242?category=941578"))
//        items.add(ContentModel("굽네치킨", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FlOnja%2Fbtq69Tmp7X4%2FoUvdIEteFbq4Z0ZtgCd4p0%2Fimg.png", "https://philosopher-chan.tistory.com/1243?category=941578"))
//        items.add(ContentModel("JMT 홈베이킹", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FNNrYR%2Fbtq64wsW5VN%2FqIaAsfmFtcvh4Bketug9m0%2Fimg.png", "https://philosopher-chan.tistory.com/1244?category=941578"))
//        items.add(ContentModel("양념장", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FK917N%2Fbtq64SP5gxj%2FNzsfNAykamW7qv1hdusp1K%2Fimg.png", "https://philosopher-chan.tistory.com/1245?category=941578"))
//        items.add(ContentModel("디톡스주스", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FeEO4sy%2Fbtq69SgK8L3%2FttCUxYHx9aPNebNwkPcI21%2Fimg.png", "https://philosopher-chan.tistory.com/1246?category=941578"))
//        items.add(ContentModel("도시락", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbdIKDG%2Fbtq64M96JFa%2FKcJiYgKuwKuP3fIyviXm90%2Fimg.png", "https://philosopher-chan.tistory.com/1247?category=941578"))
//        items.add(ContentModel("참치맛나니", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FFtY3t%2Fbtq65q6P4Zr%2FWe64GM8KzHAlGE3xQ2nDjk%2Fimg.png", "https://philosopher-chan.tistory.com/1248?category=941578"))
//        items.add(ContentModel("간장볶음면", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FOtaMq%2Fbtq67OMpk4W%2FH1cd0mda3n2wNWgVL9Dqy0%2Fimg.png", "https://philosopher-chan.tistory.com/1249?category=941578"))
    }

    private fun getBookmarkData(){
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                bookmarkIdList.clear()

                for(dataModel in dataSnapshot.children){
                    bookmarkIdList.add(dataModel.key.toString())
                }
                Log.d("ContentListActivity", bookmarkIdList.toString())
                rvAdapter.notifyDataSetChanged()

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("ContentListActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }
        FBRef.bookmarkRef.child(FBAuth.getUid()).addValueEventListener(postListener)

    }
}