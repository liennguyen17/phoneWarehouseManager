package com.ltw.phonewarehousemanager.constant;

public interface Constants {
    interface ErrorMessageSupplierValidation{
        String NOT_FIND_SUPPLIER_BY_ID = "Không tìm thấy nhà cung cấp có ID là: ";
        String NAME_NOT_BLANK = "Tên nhà cung cấp không được để trống!";
        String NAME_SIZE = "Tên nhà cung cấp không được quá 200 ký tự!";
        String ADDRESS_SIZE = "Địa chỉ không được quá 200 ký tự!";
        String ADDRESS_NOT_BLANK = "Địa chỉ không được để trống!";
        String CHECK_PHONE = "Số điện thoại không hợp lệ!";
        String START_SIZE = "Số trang phải bắt đầu từ 0!";
        String LIMIT_SIZE = "Số lượng  một nhà cung cấp là từ 5 đến 50 bài viết!";
        String DELETE_NOT_EMPTY = "Vui lòng truyền id nhà cung cấp cần xóa!";

    }

    interface ErrorMessageProductValidation{
        String NAME_NOT_SPECIAL_CHARATERS = "Tên sản phẩm không được có ký tự đặc biệt!";
        String NAME_NOT_BLANK = "Tên sản phẩm không được để trống!";
        String DESCRIPTION_NOT_BLANK = "Giới thiệu sản phẩm không được để trống!";
        String NAME_SIZE = "Tên sản phẩm không được quá 200 ký tự!";
        String DESCRIPTION_SIZE = "Giới thiệu không được quá 500 ký tự!";
        String LIMIT_SIZE = "Số lượng sản phẩm trong một trang là từ 5 đến 50 sản phẩm!";
        String START_SIZE = "Số trang phải bắt đầu từ 0!";
        String NOT_FIND_PRODUCT_BY_ID = "Không tìm thấy sản phẩm có ID là: ";
        String FORMAT_FILTER_DATE = "Thời gian phải theo định dạng 'dd/MM/yyyy' ví dụ: 25/2/2025!";
        String DELETE_NOT_EMPTY = "Vui lòng truyền id sản phẩm cần xóa!";
        String ERROR_CREAT = "Có lỗi xảy ra trong quá trình tạo sản phẩm!";
        String ERROR_QUANTITY = "số lượng tồn kho không đủ với số lượng mua ";



    }

    interface ErrorMessageStockInDetailValidate{
        String QUANTITY_NOT_NULL = "Số lượng không được để trống!";
        String PRICE_NOT_NULL = "Giá không được để trống!";
        String STOCK_IN_DATE_NOT_BLANK = "Ngày nhập hàng không được để trống!";
        String ERROR_CREAT = "Có lỗi xảy ra trong quá trình tạo phiếu nhập kho sản phẩm!";
        String NOT_FIND_STOCK_IN_DATE_BY_ID = "Không tìm thấy phiếu nhập sản phẩm có ID là: ";
        String ERROR_UPDATE = "Có lỗi xảy ra trong quá trình cập nhật phiếu nhập kho sản phẩm!";
        String LIMIT_SIZE = "Số lượng phiếu nhập kho trong một trang là từ 5 đến 50 sản phẩm!";
        String START_SIZE = "Số trang phải bắt đầu từ 0!";
        String PRICE_MIN = "Giá phải lớn hơn 0.01";
        String PRICE_MAX = "Giá không được lớn hơn 9999999999.99";
        String STOCK_ERROR = "Không tìm thấy chi tiết nhập kho với ID: ";
    }

    interface ErrorMessageStockOutDetailValidate{
        String QUANTITY_NOT_NULL = "Số lượng không được để trống!";
        String PRICE_NOT_NULL = "Giá không được để trống!";
        String STOCK_OUT_DATE_NOT_BLANK = "Ngày nhập hàng không được để trống!";
        String ERROR_CREAT = "Có lỗi xảy ra trong quá trình tạo phiếu nhập kho sản phẩm!";
        String NOT_FIND_STOCK_OUT_DATE_BY_ID = "Không tìm thấy phiếu xuất kho của sản phẩm có ID là: ";
        String ERROR_UPDATE = "Có lỗi xảy ra trong quá trình cập nhật phiếu nhập kho sản phẩm!";
        String LIMIT_SIZE = "Số lượng phiếu nhập kho trong một trang là từ 5 đến 50 sản phẩm!";
        String START_SIZE = "Số trang phải bắt đầu từ 0!";
    }

    interface ErrorMessageInvoiceDetailValidate {
        String QUANTITY_NOT_NULL = "Số lượng không được để trống!";
        String UNIT_PRICE_NOT_NULL = "Đơn giá không được để trống!";
        String UNIT_PRICE_MIN = "Đơn giá phải lớn hơn 0.01";
        String UNIT_PRICE_MAX = "Đơn giá không được lớn hơn 9999999999.99";
        String LIMIT_SIZE = "Số hóa đơn trong một trang là từ 5 đến 50!";
        String START_SIZE = "Số trang phải bắt đầu từ 0!";
    }

    interface SortType{
        String DESC = "DESC";
        String ASC = "ASC";
    }

    interface DateTimeFormatConstant{
        String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";
        String DATE_FORMAT = "dd/MM/yyyy";
        String TIME_ZONE = "Asia/Ho_Chi_Minh";
        String FORMAT_ERROR = "Định dạng ngày tháng phải là dd/mm/yyyy";
    }

    interface HeaderConstant{
        String HEADER_USER_ID = "X-USER-ID";
    }
}
