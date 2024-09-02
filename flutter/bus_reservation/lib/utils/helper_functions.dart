import 'package:flutter/material.dart';
import 'package:intl/intl.dart';

String getFormattedData(DateTime dt, {String pattern = 'dd/mm/yyyy'}) {
  return DateFormat(pattern).format(dt);
}

void showMsg(BuildContext context, String msg) => ScaffoldMessenger.of(context)
    .showSnackBar(SnackBar(content: Text('please select depature date')));
