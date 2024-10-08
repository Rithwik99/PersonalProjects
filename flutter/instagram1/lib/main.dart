import 'package:flutter/material.dart';
import 'package:instagram1/responsive/mobile_layout_screen.dart';
import 'package:instagram1/responsive/responsive_layout_screen.dart';
import 'package:instagram1/responsive/web_layout_screen.dart';
import 'package:instagram1/utils/colors.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Instagram ',
      theme: ThemeData.dark().copyWith(
        scaffoldBackgroundColor: mobileBackgroundColor,
      ),
      home: ResponsiveLayout(
        mobileScreenLayout: MobileScreenLayout(),
        webScreenLayout: WebeScreenLayout(),
      ),
    );
  }
}
