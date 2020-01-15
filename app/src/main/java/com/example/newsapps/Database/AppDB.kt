package com.example.schoolapps.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = arrayOf(News::class),version = 1)
abstract class AppDB: RoomDatabase() {

    abstract fun newsDao(): NewsDAO

    companion object{
        @Volatile
        private var INSTANCE: AppDB? = null

        fun getInstance(context: Context,scope: CoroutineScope): AppDB {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext,  AppDB::class.java,"SchoolDB")
                        .allowMainThreadQueries().fallbackToDestructiveMigration().addCallback(NewsDatabaseCallback(scope))
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }

    }

    private class NewsDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)

            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDatabase(database.newsDao())
                }
            }
        }

        suspend fun populateDatabase(newsDao: NewsDAO) {

            var news = News(1,"SMJK(C) Jit Sin II to accept students next year",
                "NIBONG TEBAL: SMJK(C) Jit Sin II is expected to open its doors next year to students, according to the " +
                        "school development committee chairman Lee Chin Heng.\n" +
                        "He said the school was established to cater to the needs of some 6,000 pupils spread across seven Chinese " +
                        "primary schools in South Seberang Perai.\n" +
                        "\n" +
                        "The construction was scheduled for completion in April this year and hope this could be done, he added.\n" +
                        "\"The plan is to start 10 classes of some 400 students for the first intake for Form One in 2017,\" he told a " +
                        "press conference today.\n" +
                        "\n" +
                        "He had accompanied state Youth and Sports, Women, Family and Community Development committee chairman Chong Eng " +
                        "on the tour of the site.\n" +
                        "\n" +
                        "Permission was granted by the Education Ministry to build the school at Bukit Valdor here in 2012 after an " +
                        "application was made in 2008.\n" +
                        "\n" +
                        "The RM30million project comprises three four-storey blocks and an eight-storey building on a 3.2ha plot.\n" +
                        "Lee appealed to Chong Eng, who heads the state Chinese and Missionary Schools Assistance Board, for funds " +
                        "to obtain furnishings and equipment.\n" +
                        "He said the school needed about RM940,000 to purchase chairs, desks and computers for students and " +
                        "teaching staff while Chong said she would bring up the matter with the state government.",
                "2019-12-21 5:30:56 PM")
            newsDao.insert(news)

            news = News(2,"Year One kids get a taste of school life",
                "GEORGE TOWN: As the new school year is set to begin just a few days away, many children, " +
                        "especially Year One pupils, have put on their Oxford blue and white uniforms and attended orientation day " +
                        "at their schools.\n" +
                        "\n" +
                        "Parents and pupils alike were either anxious or excited as they attended the " +
                        "orientation session in SJK(C) Chung Hwa Confucian (A) yesterday.\n" +
                        "\n" +
                        "Heidi Song, 41, whose twin daughters are going to the school, was a little worried as " +
                        "they were a bit shy and not used to seeing so many people in one place.\n" +
                        "\n" +
                        "As her twins Chuah Ee Jie and Ee Lynn joined about 80 other Year One pupils on their " +
                        "first ever school assembly, one broke into tears and the other child cried as well.\n" +
                        "\n" +
                        "They were calmed down by the teachers and their mother did not have to step into the assembly.\n" +
                        "\n" +
                        "“A few days ago, I already told them that they were going to experience a new environment, " +
                        "and they seemed quite receptive to it.\n" +
                        "\n" +
                        "The orientation day began with the assembly and introduction of the Year One teachers " +
                        "to the pupils.\n" +
                        "\n" +
                        "Then, the children were brought to their respective classrooms before taking part in a tour " +
                        "of the school. The parents were briefed on the fees and the curriculum.\n" +
                        "\n" +
                        "School headmaster Choong Teik Keong said the parents were also informed of the school’s extra-curricular " +
                        "activities such as karate and Cambridge English classes.",
                "2019-12-21 4:30:56 PM")
            newsDao.insert(news)

//            newsDao.deleteAll()
            news = News(3,"SJK(C) Kou Hua sees more Msian Siamese students",
                "PADANG TERAP: The 90-year-old SJK(C) Kou Hua which is the only vernacular school in the district here, " +
                        "is dominated mainly by Malaysian Siamese and Malay students.\n" +
                        "\n" +
                        "From a total of 22 Year One pupils registered at the school, 11 are Malaysian Siamese, " +
                        "10 Malay and only one Chinese pupil.\n" +
                        "\n" +
                        "The school principal, Ooi Poh Heng, said every the numbers of students has been decreasing with " +
                        "fewer numbers Chinese students.\n" +
                        "\n" +
                        "\"Only one to three Chinese students per class,” Ooi said.\n" +
                        "\n" +
                        "Overall about 55 percent of the students at the school are Malaysian Siamese, followed " +
                        "by Malay (30 per cent) and Chinese.\n" +
                        "\n" +
                        "Ooi who had been at the school for the past six months said that the situation has made teaching" +
                        " more challenging as many of the students do not have any basic knowledge of Chinese language.\n" +
                        "\n" +
                        "\"Teaching the Siamese students is especially more challenging as they do not just have any " +
                        "basic knowledge in Chinese but they also do not speak very well in Bahasa Melayu.\n" +
                        "\n" +
                        "\"To help the students master the Chinese language, we decided to outsource a Chinese language " +
                        "teacher to give tuition to the students,\" said Ooi.",
                "2019-12-21 2:30:56 PM")
            newsDao.insert(news)

            news = News(4,"School's in session for four states",
                "KUALA LUMPUR: New Year’s Day was also the first day of school for over 130,000 Year " +
                        "One pupils in Johor, Kedah, Terengganu and Kelantan today with all sorts of antics as the " +
                        "little ones bravely stepped into the new world of school.\n" +
                        "\nAccording to the 2020 School Year Calendar, these four states also recorded a total of 106,391 students to enter " +
                        "Form One for the 2020 academic year today while other states to start new school session tomorrow.\n" +
                        "\n" +
                        "In JOHOR, according to state education department, a total of 53,372 pupils started their Year One while 47,179 " +
                        "students entered Form One and 20,590 preschool children at 1,189 schools." +
                        "\nIn KEDAH, some 60,000 pupils started Year One and Form One in over 700 primary and secondary schools statewide.\n" +
                        "\n" +
                        "State Education and Human Resources Committee chairman Dr Salmee " +
                        "Said said the first day of the school session in all schools went smoothly.\n" +
                        "\n" +
                        "\nIn TERENGGANU, a total of 21,595 Year One pupils started school while 18,178 Form " +
                        "One students entered secondary schools.",
                "2020-12-31 8:30:56 PM")
            newsDao.insert(news)

            news = News(5,"Malaysia’s Chinese independent schools face uphill climb in quest for qualification recognition",
                "KUALA LUMPUR: The clock struck 3pm on a Friday afternoon and a sharp school bell pierced the quiet Taman Kaya " +
                        "neighbourhood in Kuala Lumpur. Moments later, hundreds of students " +
                        "piled out of the gates; the all-white uniform of the male students giving away their identity as pupils of " +
                        "independent high schools.\n" +
                        "\n" +
                        "On the façade of this Chong Hwa Independent School, red signage has been put up in " +
                        "commemoration of its centenary. The couplets in Chinese read: “Storms looming over the journey of " +
                        "Chinese education. We have been through 100 years and we will march on.\n\n" +
                        "Recognition of UEC has been a drawn-out battle between these schools, the government as well as anti-recognition groups.\n" +
                        "\n" +
                        "Those who are against its recognition condemn Chinese independent schools schools as a threat " +
                        "to national unity and the Malay language, for refusing to fall in line with the national education system.\n" +
                        "Pro-recognition groups cry foul over the fact that UEC has not been recognised in Malaysia, " +
                        "despite top foreign institutions such as National University of Singapore, Nanyang Technological University, " +
                        "Tokyo University and Hong Kong University long accepting it as an admission qualification.",
                "2020-1-31 7:30:56 PM")
            newsDao.insert(news)
        }
    }



}