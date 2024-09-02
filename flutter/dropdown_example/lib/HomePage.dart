import 'package:dropdown_example/city.dart';
import 'package:flutter/material.dart';

class HomePage extends StatefulWidget {
  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  String? _selectedCity;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Home Page'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text('Select a City:'),
            DropdownButton<String>(
              value: _selectedCity,
              hint: Text('Choose a city'),
              onChanged: (String? newValue) {
                setState(() {
                  _selectedCity = newValue;
                });
              },
              items: cities.map<DropdownMenuItem<String>>((String city) {
                return DropdownMenuItem<String>(
                  value: city,
                  child: Text(city),
                );
              }).toList(),
            ),
            if (_selectedCity != null)
              Padding(
                padding: const EdgeInsets.only(top: 20.0),
                child: Text('You selected: $_selectedCity'),
              ),
          ],
        ),
      ),
    );
  }
}
