import Hero from "../components/Hero";
import heroImg from "../assets/heroImg.jpg";

function Home() {
  return (
    <>
      <Hero
        cName="hero"
        heroImg={heroImg}
        title="Your Journey Your Story"
        text="Choose Your Favourite Destination."
        url="/bookflight"
        btnClass="show"
        btnText="Book Tickets"
      />
    </>
  );
}

export default Home;
