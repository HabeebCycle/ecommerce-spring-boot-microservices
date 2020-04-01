### Secure Gateway API with OAUTH2 via HTTPS

Generate certificate key at resources/keystore
```yaml
keytool -genkeypair -alias secured-gateway -keyalg RSA -keysize 2048 
-storetype PKCS12 -keystore gateway.p12 -validity 3650
```
**password**: *password*