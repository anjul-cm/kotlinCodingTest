package com.codingmountain.kotlincodingtest.ui.main.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.codingmountain.kotlincodingtest.ui.main.recyclerview.ChargingStation
import com.google.gson.Gson
import org.json.JSONArray

class ChargingStationPagingSource : PagingSource<Int, ChargingStation>() {

    //The data will come from api later
    val data = "[\n" +
            "    {\n" +
            "        \"name\": \"Thapathali EV Showroom\",\n" +
            "        \"city\": \"Kathmandu\",\n" +
            "        \"province\": \"1\",\n" +
            "        \"address\": \"Thapathali EV Showroom\",\n" +
            "        \"telephone\": \"N/A\",\n" +
            "        \"type\": [\"car\"],\n" +
            "        \"latitude\": \"27.677497605840138\", \n" +
            "        \"longitude\": \"85.31680342774558\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"name\": \"Hotel Barahi\",\n" +
            "        \"city\": \"Pokhara\",\n" +
            "        \"province\": \"3\",\n" +
            "        \"address\": \"Lakeside Rd 6\",\n" +
            "        \"telephone\": \"61460617\",\n" +
            "        \"type\": [\"car\"],\n" +
            "        \"latitude\": \"28.20833041093028\", \n" +
            "        \"longitude\": \"83.95804772177283\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"name\": \"Hundai Service (L I Auto Service)\",\n" +
            "        \"city\": \"Kathmandu\",\n" +
            "        \"province\": \"3\",\n" +
            "        \"address\": \"Hundai service, kupondole\",\n" +
            "        \"telephone\": \"01-5550380\",\n" +
            "        \"type\": [\"car\"],\n" +
            "        \"latitude\": \"27.690739168677144\", \n" +
            "        \"longitude\": \"85.31152580415048\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"name\": \"Hyundai Sales/Service Tinkune\",\n" +
            "        \"city\": \"Kathmandu\",\n" +
            "        \"province\": \"3\",\n" +
            "        \"address\": \"Hyundai Sales/Service Tinkune\",\n" +
            "        \"telephone\": \"+97714111891\",\n" +
            "        \"type\": [\"car\"],\n" +
            "        \"latitude\": \"27.69166081858411\", \n" +
            "        \"longitude\": \"85.31175442397266\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"name\": \"Labim Mall\",\n" +
            "        \"city\": \"Kathmandu\",\n" +
            "        \"province\": \"3\",\n" +
            "        \"address\": \"Pulchowk Gabahal Rd, Lalitpur\",\n" +
            "        \"telephone\": \"+97715529924\",\n" +
            "        \"type\": [\"car\"],\n" +
            "        \"latitude\": \"27.677488106320034\", \n" +
            "        \"longitude\": \"85.31666395202585\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"name\": \"Hyundai Sales Naxal\",\n" +
            "        \"city\": \"Kathmandu\",\n" +
            "        \"province\": \"3\",\n" +
            "        \"address\": \"Naxal, Kathmandu\",\n" +
            "        \"telephone\": \"+97714413934\",\n" +
            "        \"type\": [\"car\"],\n" +
            "        \"latitude\": \"27.71556151510917\", \n" +
            "        \"longitude\": \"85.33003106227773\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"name\": \"Trade Tower Thapathali\",\n" +
            "        \"city\": \"Kathmandu\",\n" +
            "        \"province\": \"3\",\n" +
            "        \"address\": \"Thapathali, Kathmandu\",\n" +
            "        \"telephone\": \"N/A\",\n" +
            "        \"type\": [\"car\"],\n" +
            "        \"latitude\": \"27.69459375947994\", \n" +
            "        \"longitude\": \"85.31994729057585\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"name\": \"Bauwa Hotel And Guest House\",\n" +
            "        \"city\": \"Bardibas\",\n" +
            "        \"province\": \"2\",\n" +
            "        \"address\": \"Bardibas\",\n" +
            "        \"telephone\": \"+9779841808341\",\n" +
            "        \"type\": [\"car\"],\n" +
            "        \"latitude\": \"26.980336417677353\", \n" +
            "        \"longitude\": \"85.91898930493367\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"name\": \"BYD Park\",\n" +
            "        \"city\": \"Kathmandu\",\n" +
            "        \"province\": \"3\",\n" +
            "        \"address\": \"Thirbam Road, BYD Park, Kathmandu\",\n" +
            "        \"telephone\": \"+97714441393\",\n" +
            "        \"type\": [\"car\"],\n" +
            "        \"latitude\": \"27.72036580899628\", \n" +
            "        \"longitude\": \"85.33131856411678\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"name\": \"Sajha Yatayat\",\n" +
            "        \"city\": \"Lalitpur\",\n" +
            "        \"province\": \"3\",\n" +
            "        \"address\": \"Pulchowk, Lalitpur\",\n" +
            "        \"telephone\": \"+97715552686\",\n" +
            "        \"type\": [\"car\"],\n" +
            "        \"latitude\": \"27.679479689424358\", \n" +
            "        \"longitude\": \"85.31745039095695\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"name\": \"Hotel Landmark Pokhara\",\n" +
            "        \"city\": \"Pokhara\",\n" +
            "        \"province\": \"Gandaki\",\n" +
            "        \"address\": \"Lakeside, Pokhara\",\n" +
            "        \"telephone\": \"+97761452908\",\n" +
            "        \"type\": [\"car\"],\n" +
            "        \"latitude\": \"28.20810501308324\", \n" +
            "        \"longitude\": \"83.95761433286081\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"name\": \"Hotel Gunja Bardibas\",\n" +
            "        \"city\": \"Pokhara\",\n" +
            "        \"province\": \"2\",\n" +
            "        \"address\": \"Bardibas\",\n" +
            "        \"telephone\": \"+97744550620\",\n" +
            "        \"type\": [\"car\"],\n" +
            "        \"latitude\": \"26.999713853956326\", \n" +
            "        \"longitude\": \"85.90329853345283\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"name\": \"Jagat Motors\",\n" +
            "        \"city\": \"Bhairawaha\",\n" +
            "        \"province\": \"Lumbini\",\n" +
            "        \"address\": \"Siddharthanagar, Bhairahawa\",\n" +
            "        \"telephone\": \"+97771525428\",\n" +
            "        \"type\": [\"car\"],\n" +
            "        \"latitude\": \"27.50386430021768\", \n" +
            "        \"longitude\": \"83.4561846502268\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"name\": \"Laxmi Dinesh 4 Wheelers\",\n" +
            "        \"city\": \"Dhangadhi\",\n" +
            "        \"province\": \"Sudurpaschim Province\",\n" +
            "        \"address\": \"Main Road, Dhangadhi\",\n" +
            "        \"telephone\": \"+97791524492\",\n" +
            "        \"type\": [\"car\"],\n" +
            "        \"latitude\": \"28.704641761732702\", \n" +
            "        \"longitude\": \"80.56778570363397\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"name\": \"Siddhartha Resort Chisapani\",\n" +
            "        \"city\": \"Chisapani\",\n" +
            "        \"province\": \"Karnali\",\n" +
            "        \"address\": \"Chisapani Chisapani\",\n" +
            "        \"telephone\": \"+97791414000\",\n" +
            "        \"type\": [\"car\"],\n" +
            "        \"latitude\": \"28.643017036827\", \n" +
            "        \"longitude\": \"81.28250618044632\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"name\": \"Waterfront Resort\",\n" +
            "        \"city\": \"Pokhara\",\n" +
            "        \"province\": \"Gandaki\",\n" +
            "        \"address\": \"Pokhara\",\n" +
            "        \"telephone\": \"+97761420004\",\n" +
            "        \"type\": [\"car\"],\n" +
            "        \"latitude\": \"28.222093460066503\", \n" +
            "        \"longitude\": \"83.95172023652376\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"name\": \"Avocado & Orchid Resort\",\n" +
            "        \"city\": \"Hetauda\",\n" +
            "        \"province\": \"Bagmati\",\n" +
            "        \"address\": \"Chaukitol -2, Hetauda Makawanpur Narayani Nepal, Tribhuvan Rajpath, Hetauda 44107\",\n" +
            "        \"telephone\": \"+97757520235\",\n" +
            "        \"type\": [\"car\"],\n" +
            "        \"latitude\": \"27.43525489831263\", \n" +
            "        \"longitude\": \"85.03541692000935\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"name\": \"Hetauda Wheels Pvt Ltd\",\n" +
            "        \"city\": \"Hetauda\",\n" +
            "        \"province\": \"Bagmati\",\n" +
            "        \"address\": \"Hetauda-10, Hetauda 44107\",\n" +
            "        \"telephone\": \"+9779855027295\",\n" +
            "        \"type\": [\"car\"],\n" +
            "        \"latitude\": \"27.42559215822066\", \n" +
            "        \"longitude\": \"85.02998818755908\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"name\": \"Hotel The Kingsbury\",\n" +
            "        \"city\": \"Birtamode\",\n" +
            "        \"province\": \"1\",\n" +
            "        \"address\": \"E - W Hwy, Birtamode 57204\",\n" +
            "        \"telephone\": \"+97723509700\",\n" +
            "        \"type\": [\"car\"],\n" +
            "        \"latitude\": \"26.639386515973417\", \n" +
            "        \"longitude\": \"87.97778701782491\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"name\": \"Bodhi Redsun-Shinee Premiere\",\n" +
            "        \"city\": \"Bhairahawa\",\n" +
            "        \"province\": \"Lumbini\",\n" +
            "        \"address\": \"Basantapur 8, Siddharthanagar 32900\",\n" +
            "        \"telephone\": \"+97771525957\",\n" +
            "        \"type\": [\"car\"],\n" +
            "        \"latitude\": \"27.51315408771957\", \n" +
            "        \"longitude\": \"83.476410364382978\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"name\": \"Hotel Da Flamingo\",\n" +
            "        \"city\": \"Butwal\",\n" +
            "        \"province\": \"Lumbini\",\n" +
            "        \"address\": \"Butwal 32907\",\n" +
            "        \"telephone\": \"+97771420152\",\n" +
            "        \"type\": [\"car\"],\n" +
            "        \"latitude\": \"27.669669310224027\", \n" +
            "        \"longitude\": \"83.46251608871846\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"name\": \"Yatri Motorcycles Experience Center\",\n" +
            "        \"city\": \"Kathmandu\",\n" +
            "        \"province\": \"Bagmati\",\n" +
            "        \"address\": \"Thirbam Sadak, Kathmandu 44600\",\n" +
            "        \"telephone\": \"+977980-1877447\",\n" +
            "        \"type\": [\"bike\"],\n" +
            "        \"latitude\": \"27.730905482675592\", \n" +
            "        \"longitude\": \"85.330016348947\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"name\": \"Team Eleven EV World- BYD SHOWROOM\",\n" +
            "        \"city\": \"Nepalgunj\",\n" +
            "        \"province\": \"Lumbini\",\n" +
            "        \"address\": \"Team Eleven EV World, Nepalgunj\",\n" +
            "        \"telephone\": \"N/A\",\n" +
            "        \"type\": [\"car\"],\n" +
            "        \"latitude\": \"28.10955066224798\", \n" +
            "        \"longitude\": \"81.65709268264737\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"name\": \"Metro Hospitality Dang\",\n" +
            "        \"city\": \"Dang\",\n" +
            "        \"province\": \"Lumbini\",\n" +
            "        \"address\": \"Tulsipur, Dang\",\n" +
            "        \"telephone\": \"+9779868282249\",\n" +
            "        \"type\": [\"car\"],\n" +
            "        \"latitude\": \"28.13355155137408\", \n" +
            "        \"longitude\": \"82.28707513285744\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"name\": \"Hyundai Sales/Service Dang\",\n" +
            "        \"city\": \"Dang\",\n" +
            "        \"province\": \"Lumbini\",\n" +
            "        \"address\": \"Hira Bhawan, Rabbe Tole, Ghorahi Lamahi - Tulsipur Road, 22415, Nepal\",\n" +
            "        \"telephone\": \"+977082563634\",\n" +
            "        \"type\": [\"car\"],\n" +
            "        \"latitude\": \"28.029439122261298\", \n" +
            "        \"longitude\": \"82.49279660346103\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"name\": \"Ocean Auto AC Charger\",\n" +
            "        \"city\": \"Butwal\",\n" +
            "        \"province\": \"Lumbini\",\n" +
            "        \"address\": \"MFPG+4WH, Butwal 32907, Nepal\",\n" +
            "        \"telephone\": \"+9779857030252\",\n" +
            "        \"type\": [\"car\"],\n" +
            "        \"latitude\": \"27.68587931026063\", \n" +
            "        \"longitude\": \"83.47720419304807\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"name\": \"Hyundai Sales/Service Butwal\",\n" +
            "        \"city\": \"Butwal\",\n" +
            "        \"province\": \"Lumbini\",\n" +
            "        \"address\": \"kalikanager, Butwal 32907, Nepal\",\n" +
            "        \"telephone\": \"+977071415278\",\n" +
            "        \"type\": [\"car\"],\n" +
            "        \"latitude\": \"27.673458261688964\", \n" +
            "        \"longitude\": \"83.46439075215936\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"name\": \"KOS Coffee & Restaurant\",\n" +
            "        \"city\": \"Dumre\",\n" +
            "        \"province\": \"Gandaki\",\n" +
            "        \"address\": \"Thumka - Korikha - Bahunbhanjyan Rd, Bandipur 33914\",\n" +
            "        \"telephone\": \"+9779856080622\",\n" +
            "        \"type\": [\"car\"],\n" +
            "        \"latitude\": \"27.966907044627355\", \n" +
            "        \"longitude\": \"84.40475111763509\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"name\": \"Gokarna Forest Resort\",\n" +
            "        \"city\": \"Kathmandu\",\n" +
            "        \"province\": \"Gandaki\",\n" +
            "        \"address\": \"Nagpokhari Marg 390, Gokarneshwor 44600\",\n" +
            "        \"telephone\": \"+977014451212\",\n" +
            "        \"type\": [\"car\"],\n" +
            "        \"latitude\": \"27.72962721027683\", \n" +
            "        \"longitude\": \"85.39870581209675\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"name\": \"Taj Riverside Resort TATA Motors 25kW DC Fast Charger\",\n" +
            "        \"city\": \"Mulkot\",\n" +
            "        \"province\": \"2\",\n" +
            "        \"address\": \"Sunkoshi Rural Municipality Mulkot-5, B.P Highway\",\n" +
            "        \"telephone\": \"+9779851088440\",\n" +
            "        \"type\": [\"car\"],\n" +
            "        \"latitude\": \"27.405068909370048\", \n" +
            "        \"longitude\": \"85.92027607898733\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"name\": \"Akshit Resort and Rafting MG ZS EV 7KW AC CHARGER\",\n" +
            "        \"city\": \"Mulkot\",\n" +
            "        \"province\": \"2\",\n" +
            "        \"address\": \"Mulkot Bazaar, Purano Jhangajholi 45400\",\n" +
            "        \"telephone\": \"+9779851045480\",\n" +
            "        \"type\": [\"car\"],\n" +
            "        \"latitude\": \"27.402325665640042\", \n" +
            "        \"longitude\": \"85.92322642403303\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"name\": \"Hyundai Sales/Service Him Auto\",\n" +
            "        \"city\": \"Nepalgunj\",\n" +
            "        \"province\": \"5\",\n" +
            "        \"address\": \"Ratna Rajmarg, Near Nepalgunj Airport, Nepalgunj\",\n" +
            "        \"telephone\": \"081-550428\",\n" +
            "        \"type\": [\"car\"],\n" +
            "        \"latitude\": \"28.097939\", \n" +
            "        \"longitude\": \"81.652359\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"name\": \"Siddhartha Hotel\",\n" +
            "        \"city\": \"Nepalgunj\",\n" +
            "        \"province\": \"5\",\n" +
            "        \"address\": \"Ratna Rajmarg, Nepalgunj\",\n" +
            "        \"telephone\": \"081-551200\",\n" +
            "        \"type\": [\"car\"],\n" +
            "        \"latitude\": \"28.085212\", \n" +
            "        \"longitude\": \"81.645616\"\n" +
            "    }\n" +
            "]"

    override fun getRefreshKey(state: PagingState<Int, ChargingStation>): Int {
        return 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ChargingStation> {
        val requiredList = mutableListOf<ChargingStation>()
        val jsonArray = JSONArray(data)
        for (i in 0 until jsonArray.length()) {
            requiredList.add(
                Gson().fromJson(
                    jsonArray.getJSONObject(i).toString(), ChargingStation::class.java
                )
            )
        }
        return LoadResult.Page(
            requiredList, null, null
        )
    }
}