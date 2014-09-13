<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>



<style type='text/css'>
.container {
  position: relative;
  width:90%;
  background-color: #cdefff;
}
nav {
  position: absolute;
  left: 0px;
  width: 200px;
  background-color: #123456;
}
section {
  /* position is static by default */
  margin-left: 200px;
  margin-right: 200px;
  background-color: #654321;
}
footer {
  position: fixed;
  bottom: 0;
  left: 0;
  height: 70px;
  background-color: white;
  width: 100%;
}
body {
	margin-bottom: 120px;
}
</style>

</head>
<body>
	
<div class="container">
  <span class="label">hjg iytyug ouyg</span>

  <nav class="elem elem-red">
    <span class="label">&lt;nav></span>
    <ul>
      <li>
        <a href="position-example.html">Home</a>
      </li>
      <li>
        <a href="position-example.html">Taco Menu</a>
      </li>
      <li>
        <a href="position-example.html">Draft List</a>
      </li>
      <li>
        <a href="position-example.html">Hours</a>
      </li>
      <li>
        <a href="position-example.html">Directions</a>
      </li>
      <li>
        <a href="position-example.html">Contact</a>
      </li>
    </ul>
    <span class="endlabel">&lt;/nav></span>
  </nav>

  <section class="elem elem-green">
    <span class="label">&lt;section></span>
    <p>
      The <code>margin-left</code> style for <code>section</code>s makes sure there is room for the <code>nav</code>. Otherwise the absolute and static elements would overlap
    </p>
    <span class="endlabel">&lt;/section></span>
  </section>

  <section class="elem elem-green ipsum">
    <span class="label">&lt;section></span>
    <p>
      Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus imperdiet, nulla et dictum interdum, nisi lorem egestas odio, vitae scelerisque enim ligula venenatis dolor. Maecenas nisl est, ultrices nec congue eget, auctor vitae massa. Fusce luctus vestibulum augue ut aliquet. Mauris ante ligula, facilisis sed ornare eu, lobortis in odio. Praesent convallis urna a lacus interdum ut hendrerit risus congue. Nunc sagittis dictum nisi, sed ullamcorper ipsum dignissim ac. In at libero sed nunc venenatis imperdiet sed ornare turpis. Donec vitae dui eget tellus gravida venenatis. Integer fringilla congue eros non fermentum. Sed dapibus pulvinar nibh tempor porta. Cras ac leo purus. Mauris quis diam velit.
    </p>
    <span class="endlabel">&lt;/section></span>
  </section>

  <section class="elem elem-green">
    <span class="label">&lt;section></span>
    <p>
      Notice what happens when you resize your browser. It works nicely!
    </p>
    <span class="endlabel">&lt;/section></span>
  </section>

  <footer class="elem elem-orange">
    <span class="label">&lt;footer></span>
    <p>
      If you use a fixed header or footer, make sure there is room for it! I put a <code>margin-bottom</code> on the <code>body</code>.
    </p>
    <span class="endlabel">&lt;/footer></span>
  </footer>

</div>




	</div>
	<footer> 8 / 19 </footer>


	</div>


</body>
</html>