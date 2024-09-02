import 'package:bus_reservation/datasource/data_source.dart';
import 'package:bus_reservation/datasource/dummyDataSource.dart';
import 'package:bus_reservation/models/but_route.dart';
import 'package:flutter/material.dart';

class AppDataProvider extends ChangeNotifier {
  final DataSource _dataSource = DummyDataSource();

  Future<BusRoute?> getRouteByCityFromAndCityTo(
      String cityFrom, String cityTo) {
    return _dataSource.getRouteByCityFromAndCityTo(cityFrom, cityTo);
  }
}
