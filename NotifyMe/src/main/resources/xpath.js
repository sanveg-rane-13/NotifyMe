             window.addEventListener("DOMContentLoaded", function() {
                document.body.addEventListener("click", function(e) {
                    var el = e.target;
                    
                    if (el.hasAttribute && el.hasAttribute("data-nofire")) {
                            return;}
                   else getElementXPath(el) ;
                }, true);
            }, false);

            getElementXPath = function(element)
{   
    console.log(element);
    if (element && element.id)
       { console.log( '//*[@id="' + element.id + '"]');
      var x=document.getElementById(element.id).innerText;
        localStorage.setItem("xpath",  element.id);
        localStorage.setItem("xpath_value",x);
        console.log(localStorage.xpath);
        console.log(x);
       }
    else{
        console.log( getElementTreeXPath(element));
        localStorage.setItem('xpath',getElementTreeXPath(element));
         console.log(localStorage.xpath);
      
         }
};

getElementTreeXPath = function(element)
{
    var paths = [];  // Use nodeName (instead of localName) 
    // so namespace prefix is included (if any).
    for (; element && element.nodeType == Node.ELEMENT_NODE; 
           element = element.parentNode)
    {
        var index = 0;
        var hasFollowingSiblings = false;
        for (var sibling = element.previousSibling; sibling; 
              sibling = sibling.previousSibling)
        {
            // Ignore document type declaration.
            if (sibling.nodeType == Node.DOCUMENT_TYPE_NODE)
                continue;

            if (sibling.nodeName == element.nodeName)
                ++index;
        }

        for (var sibling = element.nextSibling; 
            sibling && !hasFollowingSiblings;
            sibling = sibling.nextSibling)
        {
            if (sibling.nodeName == element.nodeName)
                hasFollowingSiblings = true;
        }

        var tagName = (element.prefix ? element.prefix + ":" : "") 
                          + element.localName;
        var pathIndex = (index || hasFollowingSiblings ? "[" 
                   + (index + 1) + "]" : "");
        paths.splice(0, 0, tagName + pathIndex);
    }

    return paths.length ? "/" + paths.join("/") : null;
};