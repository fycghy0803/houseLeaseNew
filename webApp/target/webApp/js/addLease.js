/**
 * Created by yulifan on 2017/6/19.
 */
getPlaceInfoById_url = "/place/getPlaceInfoById.action";
insertHiredInfo_url = "/hiredInfo/insertHiredInfo.action";
getHiredInfo_url = "/hiredInfo/getHiredInfoById.action";

$(function(){
    $("#leaseInfoForm").bootstrapValidator();
    var hhiId = UrlParm.parmValues("hhiId");
    if(hhiId) {
        $("#hhiId").val(hhiId);
        getHiredInfo(hhiId);
        $("[name=title]").html("修改出租信息");
    } else {
        var hpId = UrlParm.parmValues("hpId");
        $("#hpId").val(hpId);
        getPlaceInfo(hpId);
    }
});

getPlaceInfo = function(hpId) {
  var url = getPlaceInfoById_url;
  ajaxPost(url,"hpId=" + hpId,getPlaceInfo);
}

getPlaceInfo.ajaxSuccess = function(data) {
  if(data && data.ok) {
    var msg = $("#placeInfo").html();
    var ret = transferMes(msg,data.res);
    $("#placeInfo").html(ret);
  } else {
    alert("get place info error");
  };
};


getHiredInfo = function(hhiId) {
   var url = getHiredInfo_url;
   ajaxPost(url,"hhiId=" + hhiId,getHiredInfo);
}

getHiredInfo.ajaxSuccess = function(data) {
  if (data && data.ok) {
    transferInputs(data.res);
  } else {
    alert("get Hired Info error");
  }
}


saveLeaseInfo = function() {
    var data = $("#leaseInfoForm").data('bootstrapValidator');
    var flag = data.validate().isValid();
    if(!flag) return;
    var roomCount = $("#roomCount").val();
    var letCount = $("#letCount").val();
    var hireCount = $("#hireCount").val();

    if(roomCount < letCount + hireCount) {
       alert("房间已租满!");
       return;
    }

    var oData = $('#leaseInfoForm').serialize();
    var url = insertHiredInfo_url;
    ajaxPost(url,oData,saveLeaseInfo);
}

saveLeaseInfo.ajaxSuccess = function(data) {
    if(data && data.ok) {
       alert("操作成功!");
       window.location.href = window.location.href;
    } else {
       alert("增加租户信息失败!");
    }
}
