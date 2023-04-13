<template>
  <div class="map">
    <div>
      <h2>Perfect Play Places</h2>
      <!-- <label>
        <gmap-autocomplete @place_changed="initMarker"></gmap-autocomplete>
        <button @click="addLocationMarker">Add</button>
      </label>
      <br /> -->
    </div>
    <br />
    <gmap-map :zoom="9" :center="center">
      <gmap-marker
        :key="index"
        v-for="(m, index) in locationMarkers"
        :position="m.position"
        @click="toggleInfoWindow(m, index)"
      ></gmap-marker>
      <gmap-info-window
      :options="infoOptions"
      :position="infoWindowPos"
      :opened="infoWinOpen"
      @closeclick="infoWinOpen=false"
      >
      <div v-html="infoContent"></div>
      </gmap-info-window>
    </gmap-map>
  </div>
</template>
 
<script>
export default {
  name: "AddGoogleMap",
  data() {
    return {
      center: {
        lat: 41.50138,
        lng: -81.6935,
      },
      map: null,
      infoContent: '',
      infoWindowPos: {
        lat: 0,
        lng: 0,
      },
      infoWinOpen: false,
      currentMidx: null,
      infoOptions: {
        pixelOffset: {
          width: 0,
          height: -35,
        }
      },
      locationMarkers: [
                { name: "Cleveland",
                  description: "Edgewater Beach: The 147-acre Edgewater Park is the westernmost park in Cleveland Metroparks Lakefront Reservation. Edgewater Park features 9000 feet of shoreline, a dog beach, walking trails, picnic areas and grills and a rentable pavilion. Dogs love the park for off-leash swimming in the lake and chasing each other around. For more information, please visit ",
                  date_build: "",
                  img: require("../img/dogOnBeach1.jpg"),
                  link: "https://www.clevelandmetroparks.com/parks/visit/parks/lakefront-reservation/edgewater-beach",
                  position: {lat: 41.48892, lng: -81.74156, }
                },
                { name: "South Euclid",
                  description: "South Euclid Dog Park: This clean, well-maintained off-leash dog park is a great place for your pet to play. The park is fenced with water available during warmer months. For more information, please visit ",
                  date_build: "",
                  img: require("../img/Dogsplaying.jpg"),
                  link: "https://chparks.com/181/Dog-Park",
                  position: {lat: 41.53777, lng: -81.53192, }
                },
                { name: "Cleveland",
                  description: "Taps & Tails Dog Bar is dog-friendly bar and park in Cleveland, OH, with indoor and outdoor areas for dogs to play. Please see their website for a full list of rules before entering the park.​ For more information, please visit ",
                  date_build: "",
                  img: require("../img/dogrestaurant.jpg"),
                  link: "https://www.tapsandtailsdogbar.com/",
                  position: {lat: 41.48079, lng: -81.69775, }
                },
                { name: "Kirtland",
                  description: "Canine Meadow, Lake Metroparks' new dog park, is a place for dogs to socialize, exercise and play off-leash in a controlled environment under the supervision of their owners. This park has a large dog area (2.57 acres), a swim area (1.84 acres) and a small dog area (1.02 acres). For more information, please visit ",
                  date_build: "",
                  img: require("../img/dogwithball.jpg"),
                  link: "https://www.lakemetroparks.com/parks-trails/canine-meadow-dog-park/",
                  position: {lat: 41.58008, lng: -81.32154, }
                },
                { name: "Twinsburg",
                  description: "Your pet is welcome to play off-leash at this simple, fenced dog run in Twinsburg. For more information, please visit ",
                  date_build: "",
                  img: require("../img/gamestoplaydog.jpg"),
                  link: "https://www.mytwinsburg.com/171/Parks-Trails",
                  position: {lat: 41.31943, lng: -81.41890, }
                },
                { name: "Bay Village",
                  description: "Columbia Beach Park is a great place to bring Fido to play in the water. There's a waterfall and a place for a stroll in the grass, too. Please leash and clean up after your pets. For more information, please visit ",
                  date_build: "",
                  img: require("../img/catsonthebeach.jpg"),
                  link: "https://www.tripadvisor.com/Attraction_Review-g50063-d19402249-Reviews-Columbia_Park-Bay_Village_Ohio.html",
                  position: {lat: 41.48588, lng: -81.90153, }
                },
                { name: "Huron",
                  description: "Nickel Plate Beach is a popular dog-friendly area on the shores of Lake Erie. Leashed dogs are welcome on the beach, but are prohibited between the hours of 10am and 6pm from Memorial Day to Labor Day. Please leash and clean up after your pets. For more information, please visit ",
                  date_build: "",
                  img: require("../img/dogonbeach2.jpg"),
                  link: "https://www.cityofhuron.org/government/departments/parks-and-recreation/parks",
                  position: {lat: 41.39543, lng: -82.54483, }
                },
                { name: "Ravenna",
                  description: "West Branch Dog Park: This huge dog park is separated into a large and small section. Throughout this expansive park you'll find a pond, shady areas and grassy spots. The dog park is found within West Branch State Park. Dog owners are reminded to clean up any mess and to keep pups leashed outside of the dog park. The park is open dawn until dark. For more information, please visit ",
                  date_build: "",
                  img: require("../img/catintheforest.jpg"),
                  link: "https://ohiodnr.gov/go-and-do/plan-a-visit/find-a-property/west-branch-state-park",
                  position: {lat: 41.15162, lng: -81.14665, }
                },
                { name: "Brecksville",
                  description: "Cuyahoga Valley National Park allows leashed dogs on over 110 miles of hiking trails and 20 miles of the Towpath Trail. Pets are also welcome in the Stanford Campground. Pets must be kept on a leash no longer than six feet long at all times. Pets are not allowed in any buildings or on the Cuyahoga Valley Scenic Railroad Train. Bring extra water during the summer months as the area does get hot! For more information, please visit ",
                  date_build: "",
                  img: require("../img/doginforest2.jpg"),
                  link: "https://www.nps.gov/cuva/planyourvisit/pets.htm",
                  position: {lat: 41.31823, lng: -81.61583, }
                },
                { name: "Shaker Heights",
                  description: "Horseshoe Lake Park is dog-friendly! Fido is welcome to join you for a stroll on the 1.4 mile trail over the dam. There are paved, gravel, and boardwalk trails at the center of the park that total 1.3 miles. During your walk you will pass the historical marker for Horseshoe Lake. There is a parking area, picnic shelters, and restrooms that are open seasonally. For more information, please visit ",
                  date_build: "",
                  img: require("../img/catonthelake.jpg"),
                  link: "https://www.shakeronline.com/facilities/facility/details/Horseshoe-Lake-6",
                  position: {lat: 41.48279, lng: -81.55581, }
                },
                { name: "Wellington",
                  description: "Findley State Park is pet-friendly. This forested park is home to trails for hiking and mountain biking, a boating lake and campsites. Leashed dogs are welcome.For more information, please visit ",
                  date_build: "",
                  img: require("../img/dogsinforest1.jpg"),
                  link: "https://ohiodnr.gov/go-and-do/plan-a-visit/find-a-property/findley-state-park#:~:text=)%20647%2D2870.-,Camping,(866)%20644%2D6727.",
                  position: {lat: 41.13413, lng: -82.21870, }
                },
                { name: "North Olmstead",
                  description: "Dogs are welcome to join their humans on the trails which wind through Rocky River Reservation. Natural features of the park include shale cliffs, meadows, wetlands, and floodplain forests along with wildlife and many bird species. Dogs must be leashed at all times, and you must clean up after your pets.For more information, please visit ",
                  date_build: "",
                  img: require("../img/cat-eight.jpg"),
                  link: "https://www.clevelandmetroparks.com/parks/visit/parks/rocky-river-reservation",
                  position: {lat: 41.41034, lng: -81.88354, }
                },],
      locPlaces: [],
      existingPlace: null,
    };
  },

  mounted() {
    this.locateGeoLocation();
  },

  methods: {
    initMarker(loc) {
      this.existingPlace = loc;
    },
    addLocationMarker() {
      if (this.existingPlace) {
        const marker = {
          lat: this.existingPlace.geometry.location.lat(),
          lng: this.existingPlace.geometry.location.lng(),
        };
        this.locationMarkers.push({ position: marker });
        this.locPlaces.push(this.existingPlace);
        this.center = marker;
        this.existingPlace = null;
      }
    },
    locateGeoLocation: function () {
      navigator.geolocation.getCurrentPosition((res) => {
        this.center = {
          lat: res.coords.latitude,
          lng: res.coords.longitude,
        };
      });
    },
    toggleInfoWindow: function (locationMarkers, idx) {
      this.infoWindowPos = locationMarkers.position;
      this.infoContent = this.getInfoWindowContent(locationMarkers);
      if(this.currentMidx == idx) {
        this.infoWinOpen = !this.infoWinOpen;
      }
      else {
        this.infoWinOpen = true;
        this.currentMidx = idx;
      }
    },
    getInfoWindowContent: function (locationMarkers) {
      return (`<div class="popup" id="info-popup">
  <div class="p-image" >
    <figure class="image is-4by3">
      <img src=${locationMarkers.img} alt="Placeholder image">
    </figure>
  </div>
  <div class="card-content">
    <div class="media">
      <div class="media-content">
        <p class="title is-4">${locationMarkers.name}</p>
      </div>
    </div>
    <div class="content">
      ${locationMarkers.description}
      <br>
      <a href='${locationMarkers.link}'>${locationMarkers.link}</a>
      <br>
      <time datetime="2016-1-1">${locationMarkers.date_build}</time>
    </div>
  </div>
</div>`);
      },
    }
};
</script>

<style>
map {
  background: transparent;
}
.vue-map-container {
  justify-content: center;
  margin-left: auto;
  margin-right: auto;
  display: flex;
  width: 80vw;
  height: 600px;
}
.storemapper-iw-container{
  background-color: white;
  border-radius: 8px;
  box-shadow: 0 2px 7px 1px rgba(0,0,0,0.3);
  font-size: 13px;
  font-weight: 300;
  padding: 12px;
}

.storemapper-iw-close{
  background-position: center;
  background-repeat: no-repeat;
  background-size: 10px 10px;
  border: none;
  width: 16px;
  height: 16px;
  top: 5px;
  right: 5px;
}


</style>