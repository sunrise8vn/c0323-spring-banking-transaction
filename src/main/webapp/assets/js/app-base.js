class AppBase {
  static DOMAIN_SERVER = 'http://localhost:3300';
  static API_SERVER = this.DOMAIN_SERVER;

  static API_CUSTOMER = this.API_SERVER + '/customers';
}

class LocationRegion {
  constructor(
    id,
    provinceId,
    provinceName,
    districtId,
    districtName,
    wardId,
    wardName,
    address
  ) {
    this.id = id;
    this.provinceId = provinceId;
    this.provinceName = provinceName;
    this.districtId = districtId;
    this.districtName = districtName;
    this.wardId = wardId;
    this.wardName = wardName;
    this.address = address;
  }
}

class Customer {
  constructor(id, fullName, email, phone, locationRegion, balance, deleted) {
    this.id = id;
    this.fullName = fullName;
    this.email = email;
    this.phone = phone;
    this.locationRegion = locationRegion;
    this.balance = balance;
    this.deleted = deleted;
  }
}
