import 'dart:convert';
import 'package:ebook/views/my_tabs.dart';
import 'package:flutter/material.dart';
import 'package:ebook/colors/app_colors.dart' as AppColors;

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key}) : super(key: key);

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage>
    with SingleTickerProviderStateMixin {
  List popularBooks = [];
  bool isLoading = true;
  List books = [];
  late ScrollController _scrollController;
  late TabController _tabController;

  Future<void> readData() async {
    try {
      String jsonData = await DefaultAssetBundle.of(context)
          .loadString("json/popularBooks.json");
      setState(() {
        popularBooks = json.decode(jsonData);
      });
    } catch (error) {
      print("Error loading data: $error");
    }
    try {
      String jsonData1 =
          await DefaultAssetBundle.of(context).loadString("json/books.json");
      setState(() {
        books = json.decode(jsonData1);
      });
    } catch (error) {
      print("Error loading data: $error");
    } finally {
      setState(() {
        isLoading =
            false; // Set loading to false once data is loaded or an error occurs
      });
    }
  }

  @override
  void initState() {
    super.initState();
    _tabController = TabController(length: 3, vsync: this);
    _scrollController = ScrollController();
    readData();
  }

  @override
  void dispose() {
    _tabController.dispose();
    _scrollController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      color: AppColors.background,
      child: SafeArea(
        child: Scaffold(
          body: Column(
            children: [
              Container(
                margin: const EdgeInsets.only(left: 20, right: 20),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    ImageIcon(
                      AssetImage("assets/menu.png"),
                      size: 24,
                      color: Colors.black,
                    ),
                    Row(
                      children: [
                        Icon(Icons.search),
                        SizedBox(width: 10),
                        Icon(Icons.notifications),
                      ],
                    ),
                  ],
                ),
              ),
              SizedBox(height: 20),
              Row(
                children: [
                  Container(
                    margin: const EdgeInsets.only(left: 20),
                    child: Text(
                      "Popular Books",
                      style: TextStyle(fontSize: 30),
                    ),
                  ),
                ],
              ),
              SizedBox(height: 20),
              Container(
                height: 180,
                child: isLoading
                    ? Center(child: CircularProgressIndicator())
                    : popularBooks.isEmpty
                        ? Center(child: Text('No books available'))
                        : Stack(
                            children: [
                              Positioned(
                                top: 0,
                                left: -20,
                                right: 0,
                                child: Container(
                                  height: 180,
                                  child: PageView.builder(
                                    controller:
                                        PageController(viewportFraction: 0.8),
                                    itemCount: popularBooks.length,
                                    itemBuilder: (_, i) {
                                      return Container(
                                        height: 80,
                                        width:
                                            MediaQuery.of(context).size.width,
                                        margin:
                                            const EdgeInsets.only(right: 10),
                                        decoration: BoxDecoration(
                                          borderRadius:
                                              BorderRadius.circular(15),
                                          image: DecorationImage(
                                            image: AssetImage(
                                                popularBooks[i]["img"]),
                                            fit: BoxFit.fill,
                                          ),
                                        ),
                                      );
                                    },
                                  ),
                                ),
                              ),
                            ],
                          ),
              ),
              Expanded(
                child: NestedScrollView(
                  controller: _scrollController,
                  headerSliverBuilder: (BuildContext context, bool isScrolled) {
                    return [
                      SliverAppBar(
                        pinned: true,
                        backgroundColor: AppColors.silverBackground,
                        bottom: PreferredSize(
                          preferredSize: Size.fromHeight(50),
                          child: Container(
                            margin: const EdgeInsets.only(bottom: 20, left: 10),
                            child: TabBar(
                              indicatorPadding: const EdgeInsets.all(0),
                              indicatorSize: TabBarIndicatorSize.label,
                              labelPadding: const EdgeInsets.only(right: 10),
                              controller: _tabController,
                              isScrollable: true,
                              indicator: BoxDecoration(
                                borderRadius: BorderRadius.circular(10),
                                boxShadow: [
                                  BoxShadow(
                                    color: Colors.grey.withOpacity(0.2),
                                    blurRadius: 7,
                                    offset: Offset(0, 0),
                                  ),
                                ],
                              ),
                              tabs: [
                                AppTabs(
                                    color: AppColors.menu1Color, text: "New"),
                                AppTabs(
                                    color: AppColors.menu2Color,
                                    text: "Popular"),
                                AppTabs(
                                    color: AppColors.menu3Color, text: "Trend")
                              ],
                            ),
                          ),
                        ),
                      ),
                    ];
                  },
                  body: TabBarView(
                    controller: _tabController,
                    children: [
                      isLoading
                          ? Center(child: CircularProgressIndicator())
                          : books.isEmpty
                              ? Center(child: Text('No books available'))
                              : ListView.builder(
                                  itemCount: books.length,
                                  itemBuilder: (_, i) {
                                    return Container(
                                      margin: const EdgeInsets.only(
                                          left: 20, right: 20, top: 10),
                                      child: Container(
                                        decoration: BoxDecoration(
                                            borderRadius:
                                                BorderRadius.circular(10),
                                            color: AppColors.tabVarViewColor,
                                            boxShadow: [
                                              BoxShadow(
                                                blurRadius: 2,
                                                offset: Offset(0, 0),
                                                color: Colors.grey
                                                    .withOpacity(0.2),
                                              )
                                            ]),
                                        child: Container(
                                          padding: const EdgeInsets.all(8),
                                          child: Row(
                                            children: [
                                              Container(
                                                width: 90,
                                                height: 120,
                                                decoration: BoxDecoration(
                                                    borderRadius:
                                                        BorderRadius.circular(
                                                            10),
                                                    image: DecorationImage(
                                                      image: AssetImage(
                                                          books[i]["img"]),
                                                    )),
                                              ),
                                              SizedBox(
                                                width: 10,
                                              ),
                                              Column(
                                                crossAxisAlignment:
                                                    CrossAxisAlignment.start,
                                                children: [
                                                  Row(
                                                    children: [
                                                      Icon(
                                                        Icons.star,
                                                        size: 24,
                                                        color:
                                                            AppColors.starColor,
                                                      ),
                                                      SizedBox(
                                                        width: 5,
                                                      ),
                                                      Text(
                                                        books[i]["rating"],
                                                        style: TextStyle(
                                                            color: AppColors
                                                                .menu2Color),
                                                      )
                                                    ],
                                                  ),
                                                  Text(
                                                    books[i]["title"],
                                                    style: TextStyle(
                                                        fontSize: 16,
                                                        fontFamily: "Avenir",
                                                        fontWeight:
                                                            FontWeight.bold),
                                                  ),
                                                  Text(
                                                    books[i]["text"],
                                                    style: TextStyle(
                                                        fontSize: 16,
                                                        fontFamily: "Avenir",
                                                        color: AppColors
                                                            .subTitleText),
                                                  ),
                                                  Container(
                                                    width: 60,
                                                    height: 30,
                                                    decoration: BoxDecoration(
                                                      borderRadius:
                                                          BorderRadius.circular(
                                                              5),
                                                      color:
                                                          AppColors.loveColor,
                                                    ),
                                                    child: Text(
                                                      "Love",
                                                      style: TextStyle(
                                                          fontSize: 12,
                                                          fontFamily: "Avenir",
                                                          color: Colors.white),
                                                    ),
                                                    alignment: Alignment.center,
                                                  )
                                                ],
                                              )
                                            ],
                                          ),
                                        ),
                                      ),
                                    );
                                  }),
                      Material(
                        child: ListTile(
                          leading: CircleAvatar(
                            backgroundColor: Colors.grey,
                          ),
                          title: Text("Content"),
                        ),
                      ),
                      Material(
                        child: ListTile(
                          leading: CircleAvatar(
                            backgroundColor: Colors.grey,
                          ),
                          title: Text("Content"),
                        ),
                      ),
                    ],
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
