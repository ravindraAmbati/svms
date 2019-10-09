package svms.repository;

import svms.entity.Address;

import java.util.List;

public interface AddressRepository {

    List<Address> findAllAddress();

    Address findAddressById(Long Id);

    List<Address> findAddressByStatus(String status);

    int saveAddress(Address address);

    int deleteAddress(Long id);

    int updateAddressStatus(Long id, String status);

    int cleanUp();

}
