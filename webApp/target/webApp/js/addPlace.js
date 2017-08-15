/**
 * Created by yulifan on 2017/6/20.
 */

insertPlaceInfo_url = "/place/insertPlace.action";
getPlaceInfoById_url = "/place/getPlaceInfoById.action";

$(function(){
     $("#addPlaceForm").bootstrapValidator();
     var hpId = UrlParm.parmValues("hpId");
     if(hpId) {
       $("#hpId").val(hpId);
       getPlaceInfo(hpId);
       $("[name=title]").html("修改房屋信息");
     }
});

savePlaceInfo = function() {
    var data = $("#addPlaceForm").data('bootstrapValidator');
    var flag = data.validate().isValid();
    if(!flag) return;
    var url = insertPlaceInfo_url;
    var postData = $("#addPlaceForm").serialize();
    ajaxPost(url,postData,savePlaceInfo);
}

savePlaceInfo.ajaxSuccess = function(data) {
    if(data && data.ok)  {
       alert("操作成功!");
      window.location.href = window.location.href;
    } else {
       alert("操作失败!");
    }
}


getPlaceInfo = function(hpId) {
  var url = getPlaceInfoById_url;
  ajaxPost(url,"hpId=" + hpId,getPlaceInfo);
}

getPlaceInfo.ajaxSuccess = function(data) {
  if(data && data.ok) {
    transferInputs(data.res);
  } else {
    alert("get place info error");
  };
};
