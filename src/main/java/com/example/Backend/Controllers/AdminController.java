package com.example.Backend.Controllers;

import com.example.Backend.Models.Plan;
import com.example.Backend.Requests.*;
import com.example.Backend.Responses.Messages.Message;
import com.example.Backend.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

@RestController
@CrossOrigin
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private PlanService planService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private ProviderService providerService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private NotificationService notificationService;

    // Admin

    @PostMapping("/add/admin")
    public ResponseEntity<?> addAdmin(@ModelAttribute AdminRequest adminRequest){
        try{
            return this.adminService.add(adminRequest);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Message(e.getMessage()));
        }
    }

    @GetMapping("/info")
    public CompletableFuture<ResponseEntity<?>> getAdminInfo(@RequestParam Long id){
        return this.adminService.getAdminById(id);
    }

    @PutMapping("/update")
    public CompletableFuture<ResponseEntity<?>> editeAdmin(@RequestParam Long id, @ModelAttribute AdminUpdate admin){
        return this.adminService.edite(id,admin);
    }

    @PutMapping("/chang/password")
    public CompletableFuture<ResponseEntity<?>> changPassword(@RequestParam Long id,@ModelAttribute AdminChengPassword password){
        return this.adminService.chengPassword(id,password);
    }

    @GetMapping("/dashboard")
    public CompletableFuture<ResponseEntity<?>> dashboard(){
        try{
            return this.adminService.getDashboard();
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    // Service
    @PostMapping("/add/service")
    public CompletableFuture<ResponseEntity<?>> addService(@ModelAttribute CategoryRequest category){
        try{
            return this.categoryService.add(category.getName(),category.getImage());
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/service")
    public CompletableFuture<ResponseEntity<?>>  getService(){
        try{
            return this.categoryService.getAllToAdmin();
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @PutMapping("/edite/service")
    public CompletableFuture<ResponseEntity<?>>  updateService(@RequestParam Long id,@ModelAttribute CategoryRequest category){
        try{
            return this.categoryService.edite(id,category.getName(),category.getImage());
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @DeleteMapping("service/delete")
    public CompletableFuture<ResponseEntity<?>> deleteService(@RequestParam Long id){
        try{
            return this.categoryService.delete(id);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    //Skill
    @PostMapping("/add/skill")
    public CompletableFuture<ResponseEntity<?>> addSkill(@ModelAttribute SkillRequest skill){
        try{
            return this.skillService.add(skill.getName(),skill.getService(),skill.getImage());
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/skill")
    public CompletableFuture<ResponseEntity<?>> getSkills(){
        try{
            return this.skillService.getAll();
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @PutMapping("/edite/skill")
    public CompletableFuture<ResponseEntity<?>> updateSkill(@RequestParam Long id,@ModelAttribute SkillRequest skill){
        try{
            return this.skillService.edite(id,skill.getName(),skill.getImage(),skill.getService());
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @DeleteMapping("/skill/delete")
    public CompletableFuture<ResponseEntity<?>> deleteSkill(@RequestParam Long id){
        try{
            return this.skillService.delete(id);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    //Plans
    @PostMapping("/add/plan")
    public CompletableFuture<ResponseEntity<?>> addPlan(@ModelAttribute PlanRequest plan){
        try{
            return this.planService.add(
                    new Plan(
                            plan.getName(),
                            plan.getDuration(),
                            plan.getPrice(),
                            plan.getMaxService(),
                            plan.getMaxSkill(),
                            plan.getStatus(),
                            LocalDate.now(),
                            plan.getDescription()

            ),plan.getImage());
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/plan")
    public CompletableFuture<ResponseEntity<?>> getPlan(){
        try{
            return this.planService.getAll();
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @PutMapping("/edite/plan")
    public CompletableFuture<ResponseEntity<?>> updatePlan(@RequestParam long id,@ModelAttribute PlanRequest plan){
        try{
            return this.planService.edite(
                    id,
                    new Plan(
                            plan.getName(),
                            plan.getDuration(),
                            plan.getPrice(),
                            plan.getMaxService(),
                            plan.getMaxSkill(),
                            plan.getStatus(),
                            plan.getDescription()
                    ),
                    plan.getImage()
            );
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @DeleteMapping("/plan/delete")
    public CompletableFuture<ResponseEntity<?>> deletePlan(@RequestParam Long id){
        try{
            return this.planService.delete(id);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/plan/name")
    public CompletableFuture<ResponseEntity<?>> getPlanName(){
        return this.planService.getPlanName();
    }

    // Provider

    @PutMapping("/provider/approved")
    public CompletableFuture<ResponseEntity<?>> approvedProvider(@RequestParam Long id){
        try{
            return this.providerService.approvedProvider(id);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }


    @PutMapping("/provider/rejected")
    public CompletableFuture<ResponseEntity<?>> rejectedProvider(@RequestParam Long id,@RequestParam String reason){
        return this.providerService.rejectedProvider(id,reason);
    }

    @GetMapping("/provider")
    public CompletableFuture<ResponseEntity<?>> getAllProvider(){
        try{
            return this.providerService.getToAdminListView();
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/provider/view")
    public CompletableFuture<ResponseEntity<?>> getProviderView(@RequestParam Long id){
        try{
            return this.providerService.getToAdminView(id);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/provider/approved")
    public CompletableFuture<ResponseEntity<?>> getProviderApproved(){
        try{
            return this.providerService.getAllProviderApproved();
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/provider/pending")
    public CompletableFuture<ResponseEntity<?>> getProviderPending(){
        try{
            return this.providerService.getAllProviderPending();
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/provider/rejected")
    public CompletableFuture<ResponseEntity<?>> getProviderRejected(){
        return this.notificationService.getAllProviderRejected();
    }

    @PutMapping("/provider/block")
    public CompletableFuture<ResponseEntity<?>> blockProvider(@RequestParam Long providerId){
        return this.providerService.block(providerId);
    }

    @PutMapping("/provider/unblock")
    public CompletableFuture<ResponseEntity<?>> unblockProvider(@RequestParam Long providerId){
        return this.providerService.unblock(providerId);
    }

    // Bookings

    @GetMapping("/booking")
    public CompletableFuture<ResponseEntity<?>> getAllBooking(){
        try{
            return this.bookingService.getToAdminLists();
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @PutMapping("/booking/cancelled")
    public CompletableFuture<ResponseEntity<?>> cancelledBooking(@RequestParam Long id){
        return this.bookingService.setCancelledStatus(id);
    }

    @PutMapping("/booking/completed")
    public CompletableFuture<ResponseEntity<?>> completedBooking(@RequestParam Long id,@RequestParam float price){
        return this.bookingService.completeBooking(id,price);
    }

    @GetMapping("/booking/view")
    public CompletableFuture<ResponseEntity<?>> getBookingView(@RequestParam Long id){
        try{
            return this.bookingService.getToAdminView(id);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    // User

    @GetMapping("/user")
    public CompletableFuture<ResponseEntity<?>> getAllUser(){
        try{
           return this.userService.getToAdminList();
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/user/view")
    public CompletableFuture<ResponseEntity<?>> getUserView(@RequestParam Long  id){
        try{
            return this.userService.getToAdminView(id);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    // Transaction

    @GetMapping("/transaction")
    public CompletableFuture<ResponseEntity<?>> getAllTransaction(){
        try{
            return this.subscriptionService.getToList();
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/transaction/view")
    public CompletableFuture<ResponseEntity<?>>getTransactionView(@RequestParam Long id){
        try{
            return this.subscriptionService.getTransactionToAdminView(id);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    // Subscription

    @GetMapping("/subscription")
    public CompletableFuture<ResponseEntity<?>> getSubscriptionListView(){
        try{
            return this.subscriptionService.getAllSubscriptionListView();
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/subscription/view")
    public CompletableFuture<ResponseEntity<?>> getSubscriptionView(@RequestParam Long id){
        try {
            return this.subscriptionService.getSubscriptionView(id);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/booking/report")
    public CompletableFuture<ResponseEntity<?>> getBookingReport(){
        return this.bookingService.bookingReport();
    }

    @GetMapping("/provider/report")
    public CompletableFuture<ResponseEntity<?>> providerReport(){
        return this.providerService.getToReport();
    }

    @GetMapping("/user/report")
    public CompletableFuture<ResponseEntity<?>> getUserReport(){
        return this.userService.userReport();
    }

    @GetMapping("/service/report")
    public CompletableFuture<ResponseEntity<?>> serviceReport(){
        return this.categoryService.serviceReport();
    }

    @GetMapping("/transaction/report")
    public CompletableFuture<ResponseEntity<?>> transactionReport(){
        return this.subscriptionService.transactionReport();
    }

    @GetMapping("/plan/report")
    public CompletableFuture<ResponseEntity<?>> planReport(){
        return this.planService.getPlansReport();
    }

}
