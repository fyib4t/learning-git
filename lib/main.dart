import 'package:flutter/material.dart';
import 'background_service_controller.dart';
import 'package:permission_handler/permission_handler.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    Future<void> requestPermissions() async {
      await [
        Permission.location,
        Permission.locationWhenInUse,
      ].request();
    }

    return MaterialApp(
      title: 'Flutter Background Service',
      home: Scaffold(
        appBar: AppBar(
          title: Text('Background Service Example'),
        ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              ElevatedButton(
                onPressed: () async {
                  await requestPermissions();
                  BackgroundServiceController.startService();
                },
                child: Text('Start Service'),
              ),
              SizedBox(height: 20),
              ElevatedButton(
                onPressed: () {
                  BackgroundServiceController.stopService();
                },
                child: Text('Stop Service'),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
